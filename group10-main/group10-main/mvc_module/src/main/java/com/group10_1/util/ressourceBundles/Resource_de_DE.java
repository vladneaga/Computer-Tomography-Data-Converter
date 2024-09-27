package com.group10_1.util.ressourceBundles;



import java.util.HashMap;
import java.util.Map;
/**
 * Resource bundle for German (Germany) locale.
 * Provides localized key-value pairs for user interface elements.
 */
public class Resource_de_DE extends PublicListResourceBundle {

    /**
     * {@inheritDoc}
     * Returns a map containing the German translations for the resource bundle.
     *
     * @return a map containing the localized key-value pairs
     */
    @Override
    public Map<String, String> getContents() {
        return new HashMap<String, String>() {
            {
                // German translations for UI elements
                this.put("headerMain", "Hauptseite");
                this.put("headerPatientData", "Patientendaten");
                this.put("format", "Wählen Sie das Format: ");
                this.put("uploadCt", "Laden Sie eine .ct-Datei hoch:\n");
                this.put("uploadTxtBin", "Laden Sie eine .txt- und eine .bin-Datei hoch:");
                this.put("chooseCt", "Wählen Sie eine .ct-Datei:");
                this.put("chooseTxt", "Wählen Sie eine .txt-Datei:");
                this.put("chooseBin", "Wählen Sie eine .bin-Datei:");
                this.put("upload", "Hochladen");
                this.put("patientInfo", "Patienteninformation");
                this.put("pleaseUpload", "Bitte laden Sie die Daten hoch.");
                this.put("name", "Name: ");
                this.put("birth", "Geburtsdatum: ");
                this.put("height", "Größe: ");
                this.put("weight", "Gewicht: ");
                this.put("id", "ID: ");
                this.put("currentValueCtData", "Aktueller Wert: ");
                this.put("edit", "Bearbeiten");
                this.put("saveMusterstadt1", "Speichern Sie die Daten im Musterstadt1-Format.");
                this.put("saveMusterstadt2", "Speichern Sie die Daten im Musterstadt2-Format.");
                this.put("save", "Speichern");
                this.put("editPatientData", "Patientendaten bearbeiten");
                this.put("saveChanges", "Änderungen speichern");
            }
        };
    }
}