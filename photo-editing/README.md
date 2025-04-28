This project implements a command-line image manager in C++. It supports basic image processing operations on images loaded into memory and allows users to import, edit, transform, export, and delete images using the following commands.

Command | Description
i <filename> as <token> | Import an image file and assign it a token name. Example: i myphoto.ppm as $photo1
e <token> as <filename> | Export an image to a file (automatically appends .pgm for grayscale or .ppm for color images). Example: e $photo1 as output
d <token> | Delete the image associated with the given token.
n <token> | Invert colors of the image (negative filter).
z <token> | Equalize colors for better contrast (histogram equalization).
m <token> | Mirror the image horizontally.
g <token> | Convert a color image to grayscale. (If already grayscale, shows [NOP])
s <token> by <factor> | Scale the image brightness by a floating-point factor (must be >0 and ≤2).
r <token> clockwise <times> times | Rotate the image 90° clockwise the specified number of times.
q | Quit the application cleanly.