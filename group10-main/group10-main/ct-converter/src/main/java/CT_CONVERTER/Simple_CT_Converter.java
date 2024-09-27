package CT_CONVERTER;

import CT_CONVERTER_API.CT_Converter;
import CT_CONVERTER_API.WrongFileFormatException;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * The {@code Simple_CT_Converter} class implements the {@code CT_Converter} interface
 * and provides methods for reading and writing medical data of DataType1 and DataType2.
 *
 * This class utilizes a {@code Patient} inner class to represent patient information
 * and a map for handling key-value pairs during the conversion process.
 *
 *
 * The class supports reading and writing data from and to text, ct and binary files,
 * following the specifications of DataType1 and DataType2 formats.
 *
 *
 * Instances of this class are expected to be used for processing medical data in a
 * CT conversion context.
 *
 *
 * @author Vlad Neaga
 * @author Abdul Satar Amiri
 * @version 1.0
 */
public class Simple_CT_Converter implements CT_Converter {


    /**
     * The {@code patient} attribute holds an instance of the {@code Patient} class,
     * representing the current patient's information during the conversion process.
     */
    Patient patient;
    /**
     * The {@code keyMap} attribute is a map associating key names with bi-consumers
     *  These bi-consumers are used during to set the value of the patient object when we read the data from the files
     *  and associate it with the respective attribute. We use the key as the name of the attribute and
     *  the biconsumer that contains the direct access to set the respective attribute in order to avoid
     *  hard coding. We can just put into the map with the name of a new attribute and the corresponding bi-consumer
     *  and it will work automatically during reading of the files.
     */
    Map<String, BiConsumer<Patient, Object>> keyMap;
    /**
     * The {@code writeMap} attribute is a map associating keys from the header of the file that contain the patient data
     *  with the respective values of the patient's attributes.
     *
     *  It is very important to update the values of the map with the
     *  {@code updateWriteMap} at the begin of each writting method
     */
    Map<String, String> writeMap;
    /**
     * The {@code classLoader} variable represents an instance of the ClassLoader class
     * obtained from the ResourceLoader class's class loader.
     *
     * This variable is used to load resources and obtain their input streams during the
     * execution of the program
     */
    ClassLoader classLoader = ResourceLoader.class.getClassLoader();
    /**
     * Creates a new instance of {@code Simple_CT_Converter} with default settings.
     */
    public Simple_CT_Converter() {
        patient = new Patient();
        keyMap = new HashMap<>() {};
        updateKeyMap();
        writeMap = new LinkedHashMap<>(){};
        updateWriteMap();
    }
    /**
     * Updates the key mapping for the {@code Simple_CT_Converter}.
     *
     * This method configures the keyMap to associate keys with corresponding setters in the patient class.
     */
    public void updateKeyMap() {
        {
            keyMap.put("name", (p, v) -> p.name = (String) v);
            keyMap.put("birth", (p, v) -> p.birth = (String) v);
            keyMap.put("weight", (p, v) -> p.weight = Integer.parseInt((String) v));
            keyMap.put("height", (p, v) -> p.height = Integer.parseInt((String) v));
            keyMap.put("IZ", (p, v) -> p.IZ = (String) v);
            keyMap.put("image", (p, v) -> p.image = (String) v);
        }
    }
    /**
     * Updates the writer mapping for the {@code Simple_CT_Converter}.
     * <p>
     * This method configures the writeMap, a LinkedHashMap, to store key-value pairs
     * representing the data that will be written.
     */
    private void  updateWriteMap() {
        {
            writeMap.put("name" + '\t', patient.name);
            writeMap.put("birth" + '\t', patient.birth);
            writeMap.put("weight" + '\t', String.valueOf(patient.weight));
            writeMap.put("height" + '\t', String.valueOf(patient.height));
            writeMap.put("IZ" + '\t', String.valueOf(patient.IZ));
            writeMap.put("image" + '\t', patient.image);
            writeMap.put("", "DATA");
        }
    }
    /**
     * Reads data from a file in DataType1 format with the specified file path.
     *
     * This method reads the content of a file with the provided file path in the DataType1
     * format. The file should have a ".ct" extension. It populates the internal state of
     * the converter with the extracted information.
     *
     * @param file The file with a ".ct" extension.
     * @throws WrongFileFormatException If the file format is wrong.
     */
    @Override
    public void readFromDataType1(File file) throws WrongFileFormatException  {
        if(!file.getPath().endsWith(".ct")) throw new WrongFileFormatException("File format should be .ct!");


        // Wrap the InputStream in a BufferedReader for line reading
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("DATA")) break;//
                String finalLine = line;
                keyMap.forEach((key, value) -> {
                    if (finalLine.startsWith(key)) {
                        BiConsumer<Patient, Object> biConsumer = keyMap.get(key);
                        biConsumer.accept(patient, finalLine.replaceAll("\\t", "").replaceAll(key, ""));
                    }
                });

            }
            patient.setX(Integer.parseInt(reader.readLine()));
            patient.setY(Integer.parseInt(reader.readLine()));
            patient.setZ(Integer.parseInt(reader.readLine()));


            long counter = 0;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split(" ");
                if (!numbers[0].equals("")) {
                    short[] shortNumbers = new short[numbers.length];
                    for (int i = 0; i < numbers.length; i++) {
                        shortNumbers[i] = Short.parseShort(numbers[i]);
                    }
                    patient.intensityData.put(counter, shortNumbers);
                    counter++;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Reads data from files in DataType2 format with the specified text and binary file paths.
     *
     * This method reads the content of a text file (.txt) and a binary file (.bin) with the
     * provided file paths in the DataType2 format. It populates the internal state of the
     * converter with the extracted information.
     *
     * @param txtFile The path to the DataType2 text file with a ".txt" extension.
     * @param binFile The path to the DataType2 binary file with a ".bin" extension.
     * @throws WrongFileFormatException * @throws WrongFileFormatException If the file format is wrong.
     */
    @Override
    public void readFromDataType2(File txtFile, File binFile) throws WrongFileFormatException {
        if(!txtFile.getPath().endsWith(".txt") || !binFile.getPath().endsWith(".bin"))
            throw new WrongFileFormatException("File formats should be .txt and .bin respectively!");

        // Wrap the InputStream in a BufferedReader for line reading
        try (BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("DATA")) break;//
                String finalLine = line;
                keyMap.forEach((key, value) -> {
                    if (finalLine.startsWith(key)) {
                        BiConsumer<Patient, Object> biConsumer = keyMap.get(key);
                        biConsumer.accept(patient, finalLine.replaceAll("\\t", "").replaceAll(key, ""));
                    }
                });
            }
            patient.setX(Integer.parseInt(reader.readLine()));
            patient.setY(Integer.parseInt(reader.readLine()));
            patient.setZ(Integer.parseInt(reader.readLine()));
            reader.close();


            try (
                    FileInputStream fin = new FileInputStream(binFile);
                    DataInputStream dis = new DataInputStream(fin)) {
                for (int i = 0; i < patient.getY() * patient.getZ(); i++) {
                    short[] shortIntegers = new short[256];
                    for (int j = 0; j < patient.getX(); j++) {
                        shortIntegers[j] = dis.readShort();
                    }
                    //System.out.println(Arrays.toString(shortIntegers));
                    patient.intensityData.put((long) i, shortIntegers);
                }
                fin.close();
                dis.close();
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    /**
     * Writes data to a file in DataType1 format with the specified file path.
     *
     * This method writes the content of the converter's internal state to a file with the
     * provided file path in the DataType1 format. It generates a file with a ".ct" extension.
     *
     * @param ctFile The path to the DataType1 file with a ".ct" extension.
     * @throws WrongFileFormatException If the file format is wrong.
     */
    @Override
    public void writeDataType1(File ctFile) throws WrongFileFormatException{
        if(!ctFile.getPath().endsWith(".ct")) throw new WrongFileFormatException("File format should be .ct!");
        updateWriteMap();

        // Create the File object for the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ctFile))) {
            updateWriteMap();
            writeMap.forEach((k,v) -> {
                try {
                    writer.write(k + v);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.write(String.valueOf(patient.getX()));
            writer.newLine();
            writer.write(String.valueOf(patient.getY()));
            writer.newLine();
            writer.write(String.valueOf(patient.getZ()));
            writer.newLine();


            patient.intensityData.forEach((k, v) -> {

                StringBuilder line = new StringBuilder();
                if(v != null) {
                    for (int i = 0; i < patient.getX(); i++) {

                        line.append(v[i]);
                        if(i != patient.getX() - 1)
                            line.append(" ");
                    }
                    try {
                        writer.write(line.toString());
                        writer.newLine();
                        if((k+1) % patient.getY() == 0 ) {
                            writer.write("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Writes data to files in DataType2 format with the specified text and binary file paths.
     *
     * This method writes the content of the converter's internal state to a text file (.txt) and
     * a binary file (.bin) with the provided file paths in the DataType2 format.
     *
     * @param txtFile The path to the DataType2 text file with a ".txt" extension.
     * @param binFile The path to the DataType2 binary file with a ".bin" extension.
     * @throws WrongFileFormatException If the file formats are wrong.
     */
    @Override
    public void writeDataType2(File txtFile, File binFile) throws WrongFileFormatException{
        if(!txtFile.getPath().endsWith(".txt") || !binFile.getPath().endsWith(".bin"))
            throw new WrongFileFormatException("File formats should be .txt and .bin respectively!");

        updateWriteMap();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile))) {
            updateWriteMap();
            writeMap.forEach((k,v) -> {
                try {
                    writer.write(k + v);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.write(String.valueOf(patient.x));
            writer.newLine();
            writer.write(String.valueOf(patient.y));
            writer.newLine();
            writer.write(String.valueOf(patient.z));
            writer.newLine();
            //////////




            DataOutputStream dos = new DataOutputStream(new FileOutputStream(binFile));
            for(long i = 0; i < (long) patient.getY() * patient.getZ(); i++) {
                short[] shorts = patient.intensityData.get(i);
                for (int j = 0; j < patient.getX(); j++) {
                    dos.writeShort(shorts[j]);
                }
                //System.out.println(Arrays.toString(shortIntegers));
            }
            dos.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Writes data to file a txt file.
     *
     * This method writes the content of the converter's internal state to a text file (.txt)
     *
     * @param txtFile The path to the DataType2 text file with a ".txt" extension.
     * @throws WrongFileFormatException If the file formats are wrong.
     */
    public void writeDataType2JustTxt(File txtFile) throws WrongFileFormatException{
        if(!txtFile.getPath().endsWith(".txt"))
            throw new WrongFileFormatException("File formats should be .txt!");

        updateWriteMap();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile))) {
            updateWriteMap();
            writeMap.forEach((k,v) -> {
                try {
                    writer.write(k + v);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.write(String.valueOf(patient.x));
            writer.newLine();
            writer.write(String.valueOf(patient.y));
            writer.newLine();
            writer.write(String.valueOf(patient.z));
            writer.newLine();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * The {@code Patient} class represents information about a patient.
     *
     * It includes attributes that represent patient's data: name, birthdate, weight, height, IZ(id), image(image's link)
     * and XYZ length of the Computer Tomography picture.
     * Additionally, it maintains a map of intensity data. The key means the number of the row and the value
     * represents an array of X-length with the intensity digits of the responsive picture's part.
     *
     *
     */
    public  static class Patient {



        @Override
        public String toString() {
            return "Patient{" +
                    "name='" + name + '\'' +
                    ", birth='" + birth + '\'' +
                    ", weight=" + weight +
                    ", height=" + height +
                    ", IZ='" + IZ + '\'' +
                    ", image='" + image + '\'' +
                    ", x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }

        /**
         * The name of the patient.
         */
        private String name;

        /**
         * The birth date of the patient.
         */
        private String birth;

        /**
         * The weight of the patient.
         */
        private Integer weight;

        /**
         * The height of the patient.
         */
        private Integer height;

        /**
         * The identification code of the patient.
         */
        private String IZ;

        /**
         * The image associated with the patient.
         */
        private String image;

        /**
         * The X-coordinate in the CT scan data.
         */
        private Integer x;

        /**
         * The Y-coordinate in the CT scan data.
         */
        private Integer y;

        /**
         * The Z-coordinate in the CT scan data.
         */
        private Integer z;

        /**
         * Map containing intensity data associated with different positions.
         */
        public Map<Long, short[]> intensityData = new HashMap<>();

        /**
         * 3D array representing the CT scan data.
         */
        private short[][][] ctDataArray;


        /**
         * Gets the 3D array representing the CT scan data for the patient.
         *
         * The array is populated using the intensity data associated with different positions.
         *
         * @return The 3D array representing the CT scan data.
         */
        public short[][][] getCtDataArray() {
            this.ctDataArray = new short[z][y][x];
            int zCounterArray = 1;
            for (Long zCounter : intensityData.keySet()) {
                ctDataArray[zCounterArray - 1][(int) (zCounter % this.y)] = intensityData.get(zCounter);
                if (zCounter % this.y == this.y - 1) {
                    zCounterArray++;
                }
            }
            return ctDataArray;
        }

        /**
         * Gets the intensity data associated with different positions for the patient.
         *
         * @return The intensity data map where keys represent positions and values represent associated intensity data.
         */
        public Map<Long, short[]> getIntensityData() {
            return intensityData;
        }
        /**
         * Gets the X-coordinate value.
         *
         * @return The X-coordinate value.
         */
        public Integer getX() {
            return x;
        }

        /**
         * Sets the X-coordinate value.
         *
         * @param x The new X-coordinate value to set.
         */
        public void setX(Integer x) {
            this.x = x;
        }

        /**
         * Gets the Y-coordinate value.
         *
         * @return The Y-coordinate value.
         */
        public Integer getY() {
            return y;
        }

        /**
         * Sets the Y-coordinate value.
         *
         * @param y The new Y-coordinate value to set.
         */
        public void setY(Integer y) {
            this.y = y;
        }

        /**
         * Gets the Z-coordinate value.
         *
         * @return The Z-coordinate value.
         */
        public Integer getZ() {
            return z;
        }

        /**
         * Sets the Z-coordinate value.
         *
         * @param z The new Z-coordinate value to set.
         */
        public void setZ(Integer z) {
            this.z = z;
        }

        /**
         * Gets the name of the patient.
         *
         * @return The name of the patient.
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name of the patient.
         *
         * @param name The new name to set for the patient.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Gets the birth date of the patient.
         *
         * @return The birth date of the patient.
         */
        public String getBirth() {
            return birth;
        }

        /**
         * Sets the birth date of the patient.
         *
         * @param birth The new birth date to set for the patient.
         */
        public void setBirth(String birth) {
            this.birth = birth;
        }

        /**
         * Gets the weight of the patient.
         *
         * @return The weight of the patient.
         */
        public Integer getWeight() {
            return weight;
        }

        /**
         * Sets the weight of the patient.
         *
         * @param weight The new weight to set for the patient.
         */
        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        /**
         * Gets the height of the patient.
         *
         * @return The height of the patient.
         */
        public Integer getHeight() {
            return height;
        }

        /**
         * Sets the height of the patient.
         *
         * @param height The new height to set for the patient.
         */
        public void setHeight(Integer height) {
            this.height = height;
        }

        /**
         * Gets the IZ (Information Zone) value of the patient.
         *
         * @return The IZ value of the patient.
         */
        public String getIZ() {
            return IZ;
        }

        /**
         * Sets the IZ (Information Zone) value of the patient.
         *
         * @param IZ The new IZ value to set for the patient.
         */
        public void setIZ(String IZ) {
            this.IZ = IZ;
        }

        /**
         * Gets the image path of the patient.
         *
         * @return The image path of the patient.
         */
        public String getImage() {
            return image;
        }

        /**
         * Sets the image path of the patient.
         *
         * @param image The new image path to set for the patient.
         */
        public void setImage(String image) {
            this.image = image;
        }


    }
    /**
     * Gets the patient of the converter.
     *
     * @return The patient object.
     */
    public Patient getPatient() {
        return patient;
    }
}

