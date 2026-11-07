
package com.example.filemanagement;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Main {

    // Lecture avec BufferedReader
    public static void readFileWithBufferedReader(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture : " + e.getMessage());
        }
    }

    //  Écriture avec BufferedWriter
    public static void writeFileWithBufferedWriter(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write(content);
            bw.newLine();
            bw.write("Ligne ajoutée avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

    //  Écriture avec PrintWriter
    public static void writeWithPrintWriter(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath))) {

            pw.println("=== Rapport des employés ===");

            pw.printf("Nom: %s, Âge: %d, Poste: %s%n",
                    "Alice", 25, "Engineer");

            pw.printf("Nom: %s, Âge: %d, Poste: %s%n",
                    "Bob", 30, "Manager");

            pw.println("Fin du rapport.");

            if (pw.checkError()) {
                System.err.println("Erreur pendant l'écriture.");
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

    //  Lecture avec l'API Files
    public static void readWithFilesApi(String filePath) {
        try {

            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
    }

    //  Écriture avec l'API Files
    public static void writeWithFilesApi(String filePath, String content) {
        try {

            Path path = Paths.get(filePath);
            Files.write(path, content.getBytes());

            System.out.println("Fichier écrit avec succès : " + filePath);

        } catch (IOException e) {
            System.err.println("Erreur d'écriture : " + e.getMessage());
        }
    }

    //  Copie de fichier
    public static void copyFile(String source, String destination) {
        try {

            Path sourcePath = Paths.get(source);
            Path destPath = Paths.get(destination);

            Files.copy(sourcePath, destPath,
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Fichier copié de " + source + " vers " + destination);

        } catch (IOException e) {
            System.err.println("Erreur lors de la copie : " + e.getMessage());
        }
    }

    //  Déplacement de fichier
    public static void moveFile(String source, String destination) {
        try {

            Path sourcePath = Paths.get(source);
            Path destPath = Paths.get(destination);

            Files.move(sourcePath, destPath,
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Fichier déplacé de " + source + " vers " + destination);

        } catch (IOException e) {
            System.err.println("Erreur lors du déplacement : " + e.getMessage());
        }
    }

    //  Suppression de fichier
    public static void deleteFile(String filePath) {
        try {

            Path path = Paths.get(filePath);

            if (Files.exists(path)) {

                Files.delete(path);

                System.out.println("Fichier supprimé : " + filePath);

            } else {

                System.out.println("Le fichier n'existe pas : " + filePath);
            }

        } catch (NoSuchFileException e) {
            System.err.println("Le fichier a été supprimé entre-temps.");

        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    //  Lecture d'un fichier binaire
    public static void readBinaryFile(String filePath) {
        try (BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(filePath))) {

            byte[] buffer = new byte[1024];

            int bytesRead;
            long totalBytes = 0;

            while ((bytesRead = bis.read(buffer)) != -1) {
                totalBytes += bytesRead;
            }

            System.out.println("Fichier binaire lu : " + filePath);
            System.out.println("Taille totale : " + totalBytes + " octets");

        } catch (IOException e) {
            System.err.println("Erreur de lecture binaire : " + e.getMessage());
        }
    }

    //  Écriture d'un fichier binaire
    public static void writeBinaryFile(String filePath, byte[] data) {
        try (BufferedOutputStream bos =
                new BufferedOutputStream(new FileOutputStream(filePath))) {

            bos.write(data);
            bos.flush();

            System.out.println("Fichier binaire écrit : " + filePath);
            System.out.println("Taille écrite : " + data.length + " octets");

        } catch (IOException e) {
            System.err.println("Erreur d'écriture binaire : " + e.getMessage());
        }
    }

    //  Récupération des métadonnées
    public static void getFileMetadata(String filePath) {
        try {

            Path path = Paths.get(filePath);

            BasicFileAttributes attrs =
                    Files.readAttributes(path, BasicFileAttributes.class);

            System.out.println(" Métadonnées du fichier : " + filePath + " ");

            System.out.println("Taille : " + attrs.size() + " octets");

            System.out.println("Dernière modification : "
                    + attrs.lastModifiedTime().toInstant());

            System.out.println("Est un répertoire : " + attrs.isDirectory());

            System.out.println("Est un fichier : " + attrs.isRegularFile());

        } catch (IOException e) {
            System.err.println("Erreur métadonnées : " + e.getMessage());
        }
    }

    
    public static void main(String[] args) {
    	
        System.out.println(" Gestion des fichiers ");

        System.out.println("\n1. Lecture avec BufferedReader");
        readFileWithBufferedReader("data/employees.txt");

        System.out.println("\n2. Écriture avec BufferedWriter");
        writeFileWithBufferedWriter(
                "data/output.txt",
                "Bonjour, gestion des fichiers Java."
        );

        System.out.println("\n3. Écriture avec PrintWriter");
        writeWithPrintWriter("data/report.txt");

        System.out.println("\n4. Lecture avec Files");
        readWithFilesApi("data/employees.txt");

        System.out.println("\n5. Écriture avec Files");
        writeWithFilesApi(
                "data/nio_output.txt",
                "Fichier créé avec l'API Files."
        );

        System.out.println("\n6. Copie de fichier");
        copyFile("data/employees.txt",
                "data/employees_backup.txt");

        System.out.println("\n7. Déplacement de fichier");
        moveFile("data/nio_output.txt",
                "data/moved_output.txt");

        System.out.println("\n8. Suppression de fichier");
        deleteFile("data/moved_output.txt");

        System.out.println("\n9. Lecture binaire");
        readBinaryFile("data/test.jpg");

        System.out.println("\n10. Écriture binaire");

        byte[] data = {10, 20, 30, 40, 50};

        writeBinaryFile("data/copy_binary.bin", data);

        System.out.println("\n11. Métadonnées du fichier");
        getFileMetadata("data/employees.txt");

        System.out.println("\n FIN");
    }
}

