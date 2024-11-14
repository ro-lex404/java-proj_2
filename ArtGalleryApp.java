import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ArtGalleryApp extends JFrame {
    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JLabel displayLabel;
    private ArrayList<ImageDetails> artworkList;

    public ArtGalleryApp() {
        setTitle("Art Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);  // Larger window for gallery feel
        setLocationRelativeTo(null);

        // Set background color for a more ambient gallery look
        getContentPane().setBackground(new Color(240, 240, 240));  // Light grey background

        // Create exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        // Menu Bar with exit button
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(exitButton);
        setJMenuBar(menuBar);

        // Initialize artwork list with ImageDetails objects
        artworkList = new ArrayList<>();
        artworkList.add(new ImageDetails("images/artwork1.jpeg", "Artwork 1", "Image of two people"));
        artworkList.add(new ImageDetails("images/artwork2.jpeg", "Artwork 2", "Description for artwork 2"));
        artworkList.add(new ImageDetails("images/artwork3.jpeg", "Artwork 3", "Description for artwork 3"));
        artworkList.add(new ImageDetails("images/artwork4.jpeg", "Artwork 4", "Desc artwork 4"));
        artworkList.add(new ImageDetails("images/artwork5.jpeg", "Artwork 5", "Description for artwork 5"));
        artworkList.add(new ImageDetails("images/artwork6.jpeg", "Artwork 6", "Description for artwork 6"));
        artworkList.add(new ImageDetails("images/artwork7.jpeg", "Artwork 7", "Desc artwork 5"));
        artworkList.add(new ImageDetails("images/artwork8.jpeg", "Artwork 8", "Description for artwork 6"));
        artworkList.add(new ImageDetails("images/artwork9.jpeg", "Artwork 9", "Description for artwork 7"));
        // Add more artworks...

        // Artwork Panel with thumbnails arranged like a gallery
        JPanel artworkPanel = new JPanel();
        artworkPanel.setLayout(new BoxLayout(artworkPanel, BoxLayout.Y_AXIS));  // Use vertical layout for thumbnails
        artworkPanel.setBackground(new Color(240, 240, 240));  // Light background for the artwork panel

        for (ImageDetails imageDetails : artworkList) {
            JPanel thumbnailPanel = new JPanel();

            if (!imageDetails.exists()) {
                System.out.println("File not found: " + imageDetails.getImageFile().getAbsolutePath());
                continue; // Skip this image if not found
            }

            // Load and scale image with a border to mimic a real frame
            ImageIcon icon = new ImageIcon(imageDetails.getImageFile().getAbsolutePath());
            Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));
            label.setPreferredSize(new Dimension(200, 200));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));  // Frame-like border

            // Add mouse click listener for each thumbnail
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateDetails(imageDetails);
                }
            });

            thumbnailPanel.add(label);
            artworkPanel.add(thumbnailPanel);
        }

        // Add the artwork panel to a JScrollPane for scrolling functionality
        JScrollPane scrollPane = new JScrollPane(artworkPanel);
        scrollPane.setPreferredSize(new Dimension(300, 600));  // Set preferred size for the scrollable area
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Display Panel for full-sized image (with a gallery frame effect)
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayLabel = new JLabel();
        displayLabel.setHorizontalAlignment(JLabel.CENTER);
        displayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // Simulate gallery frame
        displayPanel.add(displayLabel, BorderLayout.CENTER);
        getContentPane().add(displayPanel, BorderLayout.WEST);

        // Details Panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Title: ");
        descriptionArea = new JTextArea("Select an artwork to see its details...");
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(240, 240, 240));  // Matching background color
        JButton buyButton = new JButton("Buy Now");

        detailsPanel.add(titleLabel, BorderLayout.NORTH);
        detailsPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        detailsPanel.add(buyButton, BorderLayout.SOUTH);
        getContentPane().add(detailsPanel, BorderLayout.EAST);

        // Buy Button Action
        buyButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Purchase flow initiated!"));
    }

    // Method to update details panel with artwork info and show full-sized image
    private void updateDetails(ImageDetails imageDetails) {
        titleLabel.setText("Title: " + imageDetails.getTitle());
        descriptionArea.setText(imageDetails.getDescription());

        ImageIcon fullImageIcon = new ImageIcon(imageDetails.getImageFile().getAbsolutePath());
        Image scaledImage = fullImageIcon.getImage().getScaledInstance(600, 800, Image.SCALE_SMOOTH);
        displayLabel.setIcon(new ImageIcon(scaledImage));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showLoginScreen());
    }

    // Display Login Screen
    public static void showLoginScreen() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            // Simple check (no backend verification)
            if (username.equals("admin") && password.equals("password")) {
                loginFrame.dispose(); // Close login window
                new ArtGalleryApp().setVisible(true); // Open main app window
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid credentials, try again.");
            }
        });

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(new JLabel()); // Empty cell
        panel.add(loginButton);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }
}
