package com.group10_1.util.ressourceBundles;



import java.util.HashMap;
import java.util.Map;

/**
 * Resource bundle for English (United States) locale.
 * Provides localized key-value pairs for user interface elements.
 */
public class Resource_en_US extends PublicListResourceBundle {

    /**
     * {@inheritDoc}
     * Returns a map containing the English translations for the resource bundle.
     *
     * @return a map containing the localized key-value pairs
     */
    @Override
    public Map<String, String> getContents() {
        return new HashMap<String, String>() {
            {
                // English translations for UI elements
                this.put("headerMain", "Main");
                this.put("headerPatientData", "Patient Data");
                this.put("format", "Choose the format: ");
                this.put("uploadCt", "Upload a .ct file:\n");
                this.put("uploadTxtBin", "Upload a .txt and a.bin file:");
                this.put("chooseCt", "Choose a .ct file:");
                this.put("chooseTxt","Choose a .txt file:");
                this.put("chooseBin","Choose a .bin file:");
                this.put("upload", "Upload");
                this.put("patientInfo", "Patient Information");
                this.put("pleaseUpload", "Please upload the data.");
                this.put("name","Name: ");
                this.put("birth","Birth Date: ");
                this.put("height", "Height: ");
                this.put("weight", "Weight: ");
                this.put("id", "ID: ");
                this.put("currentValueCtData","Current Value: ");
                this.put("edit","Edit");
                this.put("saveMusterstadt1", "Save the data in the Musterstadt1 format.");
                this.put("saveMusterstadt2", "Save the data in the Musterstadt2 format.");
                this.put("save", "Save");
                this.put("editPatientData","Edit Patient Data");
                this.put("saveChanges","Save Changes");
            }
        };
    }
}

