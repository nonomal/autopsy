/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2014 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.ingest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openide.util.NbBundle;
import org.sleuthkit.datamodel.Content;

/**
 * This class manages a sequence of data source level ingest modules for a data
 * source ingest job. It starts the modules, runs data sources through them, and
 * shuts them down when data source level ingest is complete.
 * <p>
 * This class is not thread-safe.
 */
final class DataSourceIngestPipeline {

    private static final IngestManager ingestManager = IngestManager.getInstance();
    private final DataSourceIngestJob job;
    private final List<PipelineModule> modules = new ArrayList<>();
    private volatile PipelineModule currentModule;
    private boolean running;

    /**
     * Constructs an object that manages a sequence of data source level ingest
     * modules. It starts the modules, runs data sources through them, and shuts
     * them down when data source level ingest is complete.
     *
     * @param job The data source ingest job to which this pipeline belongs.
     * @param moduleTemplates The ingest module templates that define the
     * pipeline.
     */
    DataSourceIngestPipeline(DataSourceIngestJob job, List<IngestModuleTemplate> moduleTemplates) {
        this.job = job;

        /**
         * Create a data source level ingest module instance from each ingest
         * module template.
         */
        for (IngestModuleTemplate template : moduleTemplates) {
            if (template.isDataSourceIngestModuleTemplate()) {
                PipelineModule module = new PipelineModule(template.createDataSourceIngestModule(), template.getModuleName());
                modules.add(module);
            }
        }
    }

    /**
     * Indicates whether or not there are any ingest modules in this pipeline.
     *
     * @return True or false.
     */
    boolean isEmpty() {
        return modules.isEmpty();
    }

    /**
     * Starts up the ingest module in this pipeline.
     *
     * @return A list of ingest module startup errors, possibly empty.
     */
    List<IngestModuleError> startUp() {
        if (this.running) {
            throw new IllegalStateException("Attempt to start up a pipeline that is already running"); //NON-NLS
        }

        List<IngestModuleError> errors = new ArrayList<>();
        for (PipelineModule module : modules) {
            try {
                module.startUp(new IngestJobContext(this.job));
            } catch (Throwable ex) { // Catch-all exception firewall
                errors.add(new IngestModuleError(module.getDisplayName(), ex));
            }
        }
        return errors;
    }

    /**
     * Runs a data source through the ingest modules in sequential order.
     *
     * @param task A data source level ingest task containing a data source to
     * be processed.
     * @return A list of processing errors, possible empty.
     */
    List<IngestModuleError> process(DataSourceIngestTask task) {
        if (!this.running) {
            throw new IllegalStateException("Attempt to process with pipeline that is not running"); //NON-NLS
        }

        List<IngestModuleError> errors = new ArrayList<>();
        Content dataSource = task.getDataSource();
        for (PipelineModule module : modules) {
            try {
                module.setStartTime();
                this.currentModule = module;
                String displayName = NbBundle.getMessage(this.getClass(),
                        "IngestJob.progress.dataSourceIngest.displayName",
                        module.getDisplayName(), dataSource.getName());
                this.job.updateDataSourceIngestProgressBarDisplayName(displayName);
                this.job.switchDataSourceIngestProgressBarToIndeterminate();
                ingestManager.setIngestTaskProgress(task, module.getDisplayName());
                module.process(dataSource, new DataSourceIngestModuleProgress(this.job));
            } catch (Throwable ex) { // Catch-all exception firewall
                errors.add(new IngestModuleError(module.getDisplayName(), ex));
            }
            if (this.job.isCancelled()) {
                break;
            } else if (this.job.currentDataSourceIngestModuleIsCancelled()) {
                this.job.currentDataSourceIngestModuleCancellationCompleted();
            }
        }
        this.currentModule = null;
        ingestManager.setIngestTaskProgressCompleted(task);
        return errors;
    }

    /**
     * Gets the currently running module.
     *
     * @return The module, possibly null.
     */
    PipelineModule getCurrentlyRunningModule() {
        return this.currentModule;
    }

    /**
     * This class decorates a data source level ingest module with a display
     * name and a start time.
     */
    static class PipelineModule implements DataSourceIngestModule {

        private final DataSourceIngestModule module;
        private final String displayName;
        private Date startTime;

        /**
         * Constructs an object that decorates a data source level ingest module
         * with a display name and a running time.
         *
         * @param module The data source level ingest module to be decorated.
         * @param displayName The display name.
         */
        PipelineModule(DataSourceIngestModule module, String displayName) {
            this.module = module;
            this.displayName = displayName;
            this.startTime = new Date();
        }

        /**
         * Gets the class name of the decorated ingest module.
         *
         * @return The class name.
         */
        String getClassName() {
            return this.module.getClass().getCanonicalName();
        }

        /**
         * Gets a module name suitable for display in a UI.
         *
         * @return The display name.
         */
        String getDisplayName() {
            return this.displayName;
        }

        /**
         * Sets the start time to the current time.
         */
        void setStartTime() {
            this.startTime = new Date();
        }

        /**
         * Gets the time the decorated ingest module started processing the data
         * source.
         *
         * @return The start time.
         */
        Date getStartTime() {
            return this.startTime;
        }

        /**
         * @inheritDoc
         */
        @Override
        public void startUp(IngestJobContext context) throws IngestModuleException {
            this.module.startUp(context);
        }

        /**
         * @inheritDoc
         */
        @Override
        public IngestModule.ProcessResult process(Content dataSource, DataSourceIngestModuleProgress statusHelper) {
            this.startTime = new Date();
            return this.module.process(dataSource, statusHelper);
        }

    }

}
