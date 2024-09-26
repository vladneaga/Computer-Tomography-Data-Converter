The CT Converter Site is a web application that allows users to upload CT scan data, convert it between two specific formats, and download the results. The site is designed for easy use and supports both English and German languages. You can upload CT data in either .ct format or in a combination of .txt and .bin files, view the uploaded data, convert between the two formats, visualize the CT data in 3 axes, and download the converted data.

Features
Upload CT Data: Upload CT scan data in one of the two supported formats:

Format 1: A single .ct file containing metadata and CT intensity values.
Format 2: A .txt file for metadata and a separate .bin file for CT intensity values.
View Data: After uploading, you can view the metadata, CT scan dimensions, and 3D visualization of the data along the X, Y, and Z axes.

3-Axis Visualization: View the CT data in 3 separate planes (X, Y, and Z axes), allowing a clear visualization of the image slices from each perspective.

Convert Data: Convert between the two formats seamlessly.

Download Converted Data: After conversion, download the results in the desired format.

Language Support: Switch between English and German interfaces for ease of use.

File Formats
Format 1: .ct File
A single .ct file contains both the metadata and the CT intensity values.

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
DATA: Dimensions of the CT scan (x, y, z axes).
The intensity values follow the metadata, representing the CT data.
Format 2: .txt and .bin Files
In this format, the metadata and CT intensity values are stored separately.

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
A binary file that contains the CT intensity values.
Usage Instructions
Upload CT Data:

Choose whether to upload in .ct format or .txt and .bin format.
View Uploaded Data:

After uploading, the metadata, dimensions, and 3-axis visualization of the CT scan will be displayed for review.
Visualize Data:

The application provides 3D visualization of the CT scan data by allowing you to view image slices along the X, Y, and Z axes separately.
Convert Data:

Click on the Convert button to switch between the two supported formats.
Download Converted Data:

Once converted, you can download the data in the new format.
Switch Language:

The application supports both English and German. You can toggle the language from the top menu.
Language Support
The site supports both English and German interfaces.
Users can switch between these languages at any time for ease of use.
Technology Stack
Frontend: HTML, CSS, JavaScript (with language toggle support).
Backend: SprinBoot (Java)
File Handling: Converts .ct files to .txt and .bin formats and vice versa.
