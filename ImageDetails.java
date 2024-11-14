import java.io.File;

public class ImageDetails {
    private File imageFile;
    private String title;
    private String description;

    // Constructor
    public ImageDetails(String imagePath, String title, String description) {
        this.imageFile = new File(imagePath);
        this.title = title;
        this.description = description;
    }

    // Getter for the image file
    public File getImageFile() {
        return imageFile;
    }

    // Getter for the title
    public String getTitle() {
        return title;
    }

    // Getter for the description
    public String getDescription() {
        return description;
    }

    // Method to check if the image file exists
    public boolean exists() {
        return imageFile.exists();
    }
}
