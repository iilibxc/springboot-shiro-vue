package code.util;

import java.io.*;

public class DeleteSystemOuts {
    private static int count = 0;

    public static void clear(File file, String charset) {
        try {
            if (!file.exists()) {
                return;
            }
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    clear(f, charset);
                }
                return;
            } else if (!file.getName().endsWith(".java")) {
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
            StringBuffer content = new StringBuffer();
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                content.append(temp);
                content.append("");
            }
            String target = content.toString();
            String s = target.replaceAll("System.out.println.+", "");
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
            out.write(s);
            out.flush();
            out.close();
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clear(String filePath, String charset) {
        clear(new File(filePath), charset);
    }

    public static void clear(String filePath) {
        clear(new File(filePath), "UTF-8");
    }

    public static void clear(File file) {
        clear(file, "UTF-8");
    }

    public static void main(String[] args) {
        clear("D:codesrcmainjavacomglarun");
        System.out.println(count);
    }
}
