The CT Converter Site is a web application that allows users to upload, view, convert, edit metadata, and download CT scan data in two specific formats. Designed for ease of use, the site supports both English and German languages. Users can upload their CT data in either a single .ct file or a combination of .txt and .bin files, view and edit the metadata, visualize the data in 3D across three axes, convert between formats, and download the results.

All user actions, such as uploading, editing, converting, and downloading, are logged for auditing purposes.

Features
Upload CT Data: Supports two formats:

Format 1: A single .ct file containing metadata and CT intensity values.
Format 2: A combination of .txt (metadata) and .bin (CT intensity values) files.
View and Edit Metadata: After uploading, users can view the metadata (e.g., name, birthdate, weight, height) and have the option to edit this information directly in the web interface.

3-Axis Visualization: Visualize the CT scan data along the X, Y, and Z axes, showing the 3D structure of the CT slices.

Convert Data: Seamlessly convert between the two formats:

From .ct to .txt and .bin
From .txt and .bin to .ct
Download Converted Data: After conversion, users can download the CT data in the new format.

Language Support: The interface is available in both English and German. Users can switch between languages at any time.

Action Logging: All user actions, including uploads, metadata edits, conversions, and downloads, are logged for tracking and auditing purposes.

File Formats
Format 1: .ct File
A single .ct file that contains both the metadata and the CT intensity values.

Example Structure:

arduino
Копировать код
name    Michael Schöll
birth   29.06.1998
weight  103
height  204
IZ      100459ASM98
image   null
DATA
256
256
175
<CT intensity values>
DATA: Contains the dimensions of the CT scan (X, Y, Z axes).
The intensity values follow the metadata, representing the CT data.
Format 2: .txt and .bin Files
In this format, the metadata and CT intensity values are stored in separate files.

Metadata File (.txt):
arduino
Копировать код
name    Michael Schöll
birth   29.06.1998
weight  103
height  204
IZ      100459ASM98
image   null
DATA
256
256
175
CT Intensity File (.bin):
A binary file that contains the actual CT intensity values.
Sample files are available at:
SWLAB_AUFGABE2_MULTI_MODULE/ct-converter/target/classes/sample-files

Usage Instructions
Upload CT Data:
Choose the file format you want to upload:
Format 1: Upload a .ct file.
Format 2: Upload a .txt file for metadata and a .bin file for intensity values.
View and Edit Metadata:
After uploading, the metadata and dimensions of the CT scan are displayed. Users can edit the metadata directly in the interface, updating fields such as name, birthdate, weight, and height.
Visualize Data:
The application provides 3D visualization of the CT data, showing slices along the X, Y, and Z axes separately.
Convert Data:
Click the Convert button to switch between the supported formats:
.ct → .txt and .bin
.txt and .bin → .ct
Download Converted Data:
After converting, users can download the converted files in the newly selected format.
Switch Language:
Toggle between English and German via the language switcher in the top menu.
Language Support
English and German are supported.
Users can switch between languages at any time to access the interface in their preferred language.
Technology Stack
Frontend: HTML, CSS, JavaScript (support for language switching between English and German).
Backend: Spring Boot (Java) for handling file uploads, conversions, metadata editing, and logging user actions.
File Handling: Converts .ct files to .txt and .bin formats, and vice versa.
3D Visualization: Visualizes the CT scan data along the X, Y, and Z axes separately.

Getting Started
Prerequisites
Java 11+
Maven
Installation

Clone the repository:
git clone <repository-url>

Build the project:
mvn clean install

Run the application:
java -jar target/ct-converter-0.0.1-SNAPSHOT.jar

Open your browser and go to:
http://localhost:8080
Logging
All user actions are logged, including:
File uploads
Metadata edits
Format conversions
File downloads
These logs help track system usage and ensure all activities are properly monitored.


