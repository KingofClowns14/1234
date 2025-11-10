import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NioFileOperations {
    public static void main(String[] args) {
        String surname = "Kurochkin";
        String name = "Ilya";
        try {
            // a)Создайте директорию <surname>, где <surname> – ваша фамилия
            Path surnameDir = Paths.get(surname);
            Files.createDirectories(surnameDir);
            System.out.println("Директория " + surname + " создана");
            // b)Внутри директории <surname> создайте файл с названием <name>,где <name> –
            // ваше имя
            Path nameFile = surnameDir.resolve(name + ".txt");
            if (!Files.exists(nameFile)) {
                Files.createFile(nameFile);
                System.out.println("Файл" + name + ".txt создан в директории" + surname);
            }
            // c) Внутри вашей директории <surname> создайте вложенные директории dir1/
            // dir2/ dir3 и скопируйте туда ваш файл <name>
            Path dir1 = surnameDir.resolve("dir1");
            Path dir2 = surnameDir.resolve("dir2");
            Path dir3 = surnameDir.resolve("dir3");
            Files.createDirectories(dir1);
            Files.createDirectories(dir2);
            Files.createDirectories(dir3);
            System.out.println("Вложенные директории dir1 dir2 dir3 созданы");
            Files.copy(nameFile, dir1.resolve(name + ".txt"), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(nameFile, dir2.resolve(name + ".txt"), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(nameFile, dir3.resolve(name + ".txt"), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован в dir1 dir2 dir3");
            // d)Внутри директории dir1 создайте файл file1
            Path file1 = dir1.resolve("file1.txt");
            if (!Files.exists(file1)) {
                Files.createFile(file1);
                System.out.println("Файл file1 создан в dir1");
            }
            // e)Внутри директории dir2 создайте файл file2.
            Path file2 = dir2.resolve("file2.txt");
            if (!Files.exists(file2)) {
                Files.createFile(file2);
                System.out.println("Файл file2 создан в dir2");
            }
            // f)Выполните рекурсивный обход вашей директории <surname> и выведите названия
            // всех файлов и директорий, которые там есть.
            // При выводе перед именем файла поставьте пометку «F», перед именем директории
            // – «D».
            System.out.println("Рекурсивный обход директории - " + surname);
            Files.walkFileTree(surnameDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("-----------");
                    System.out.println("Directory - " + dir.getFileName());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("File - " + file.getFileName());
                    return FileVisitResult.CONTINUE;
                }
            });
            System.out.println("Обход завершен");
            // g)Удалите директорию dir1 со всем ее содержимым
            Files.walkFileTree(dir1, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            System.out.println("Директория dir1 удаленаа вместе с её содержимым");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
