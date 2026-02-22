// Aurelio Rafi

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class PicUploader extends JFrame {

    public PicUploader() {

        setTitle("Foto Uploader Sederhana");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        
        JButton uploadButton = new JButton("Pilih & Upload Foto");
        uploadButton.setBounds(100, 60, 200, 40);
        add(uploadButton);

        
        uploadButton.addActionListener(e -> uploadPhoto());

        setVisible(true);
    }

    private void uploadPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            File uploadDir = new File("uploads");

            
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            Path sourcePath = selectedFile.toPath();
            Path targetPath = Paths.get(uploadDir.getPath(), selectedFile.getName());

            try {
                
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(this, "Foto berhasil disimpan di:\n" + targetPath.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal menyimpan foto.");
            }
        }
    }

    public static void main(String[] args) {
        new PicUploader();
    }
}

