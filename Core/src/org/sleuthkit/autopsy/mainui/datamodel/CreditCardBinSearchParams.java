/*
 * Autopsy Forensic Browser
 *
 * Copyright 2021 Basis Technology Corp.
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
package org.sleuthkit.autopsy.mainui.datamodel;

import java.util.Objects;

/**
 * Search params to fetch credit cards by bin prefix.
 */
public class CreditCardBinSearchParams extends CreditCardSearchParams {

    private static final String TYPE_ID = "CREDIT_CARD_BY_BIN";

    /**
     * @return The type id for this search parameter.
     */
    public static String getTypeId() {
        return TYPE_ID;
    }

    private final String binPrefix;

    public CreditCardBinSearchParams(String binPrefix, boolean includeRejected, Long dataSourceId) {
        super(includeRejected, dataSourceId);
        this.binPrefix = binPrefix;
    }

    public String getBinPrefix() {
        return binPrefix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.binPrefix);
        hash = 47 * hash + super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CreditCardBinSearchParams other = (CreditCardBinSearchParams) obj;
        if (!Objects.equals(this.binPrefix, other.binPrefix)) {
            return false;
        }
        return super.equals(obj);
    }

    
}
