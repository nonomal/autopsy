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
import org.sleuthkit.datamodel.AbstractFile;

/**
 * This class manages a sequence of file level ingest modules for a data source
 * ingest job. It starts the modules, runs files through them, and shuts them
 * down when file level ingest is complete.
 * <p>
 * This class is not thread-safe, it is intended to be used by one file ingest
 * thread at at time. However, the running flag is volatile since it may be read
 * by another thread looking for a progress snapshot.
 */
final class FileIngestPipeline {

    private static final IngestManager ingestManager = IngestManager.getInstance();
    private final DataSourceIngestJob job;
    private final List<PipelineModule> modules = new ArrayList<>();
    private Date startTime;
    private volatile boolean running;

    /**
     * Constructs an object that manages a sequence of file level ingest
     * modules. It starts the modules, runs files through them, and shuts them
     * down when file level ingest is complete.
     *
     * @param job The data source ingest job that owns the pipeline.
     * @param moduleTemplates The ingest module templates that define the
     * pipeline.
     */
    FileIngestPipeline(DataSourceIngestJob job, List<IngestModuleTemplate> moduleTemplates) {
        this.job = job;

        /**
         * Create an ingest module instance from each file ingest module
         * template and add it to the pipeline.
         */
        for (IngestModuleTemplate template : moduleTemplates) {
            if (template.isFileIngestModuleTemplate()) {
                PipelineModule module = new PipelineModule(template.createFileIngestModule(), template.getModuleName());
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
        return this.modules.isEmpty();
    }

    /**
     * Queries whether or not this file ingest level pipeline is running.
     *
     * @return True or false.
     */
    boolean isRunning() {
        return this.running;
    }

    /**
     * Starts up all of the ingest modules in the pipeline.
     *
     * @return List of start up errors, possibly empty.
     */
    List<IngestModuleError> startUp() {
        if (this.running) {
            throw new IllegalStateException("Attempt to start up a pipeline that is already running"); //NON-NLS
        }
        this.running = true;
        this.startTime = new Date();
                
        List<IngestModuleError> errors = new ArrayList<>();
        for (PipelineModule module : this.modules) {
            try {
                module.startUp(new IngestJobContext(this.job));
            } catch (Throwable ex) { // Catch-all exception firewall
                errors.add(new IngestModuleError(module.getDisplayName(), ex));
            }
        }
        return errors;
    }

    /**
     * Returns the start up time of the pipeline.
     *
     * @return The file processing start time, may be null.
     */
    Date getStartTime() {
        return this.startTime;
    }

    /**
     * Runs a file through the ingest modules in sequential order.
     *
     * @param task A file level ingest task containing a file to be processed.
     * @return A list of processing errors, possible empty.
     */
    List<IngestModuleError> process(FileIngestTask task) {
        if (!this.running) {
            throw new IllegalStateException("Attempt to process file with pipeline that is not running"); //NON-NLS
        }

        List<IngestModuleError> errors = new ArrayList<>();
        AbstractFile file = task.getFile();
        for (PipelineModule module : this.modules) {
            try {
                FileIngestPipeline.ingestManager.setIngestTaskProgress(task, module.getDisplayName());
                module.process(file);
            } catch (Throwable ex) { // Catch-all exception firewall
                errors.add(new IngestModuleError(module.getDisplayName(), ex));
            }
            if (this.job.isCancelled()) {
                break;
            }
        }
        file.close();
        if (!this.job.isCancelled()) {
            IngestManager.getInstance().fireFileIngestDone(file);
        }
        FileIngestPipeline.ingestManager.setIngestTaskProgressCompleted(task);
        return errors;
    }

    /**
     * Shuts down all of the modules in the pipeline.
     *
     * @return A list of shut down errors, possibly empty.
     */
    List<IngestModuleError> shutDown() {
        if (!this.running) {
            throw new IllegalStateException("Attempt to shut down a pipeline that is not running"); //NON-NLS
        }

        List<IngestModuleError> errors = new ArrayList<>();
        for (PipelineModule module : this.modules) {
            try {
                module.shutDown();
            } catch (Throwable ex) { // Catch-all exception firewall
                errors.add(new IngestModuleError(module.getDisplayName(), ex));
            }
        }
        this.running = false;
        return errors;
    }

    /**
     * This class decorates a file level ingest module with a display name.
     */
    private static final class PipelineModule implements FileIngestModule {

        private final FileIngestModule module;
        private final String displayName;

        /**
         * Constructs an object that decorates a file level ingest module with a
         * display name.
         *
         * @param module The file level ingest module to be decorated.
         * @param displayName The display name.
         */
        PipelineModule(FileIngestModule module, String displayName) {
            this.module = module;
            this.displayName = displayName;
        }

        /**
         * Gets the class name of the decorated ingest module.
         *
         * @return The class name.
         */
        String getClassName() {
            return module.getClass().getCanonicalName();
        }

        /**
         * Gets display name of the decorated ingest module.
         *
         * @return The display name.
         */
        String getDisplayName() {
            return displayName;
        }

        /**
         * @inheritDoc
         */
        @Override
        public void startUp(IngestJobContext context) throws IngestModuleException {
            module.startUp(context);
        }

        /**
         * @inheritDoc
         */
        @Override
        public IngestModule.ProcessResult process(AbstractFile file) {
            return module.process(file);
        }

        /**
         * @inheritDoc
         */
        @Override
        public void shutDown() {
            module.shutDown();
        }

    }

}
