package domain.upload;
public class S3Storage implements Storage {
    public String salvar(String arquivoLocal) {
        String url = "https://s3.mock/bucket/" + System.nanoTime() + "-" + arquivoLocal;
        System.out.println("Enviando para S3 -> " + url);
        return url;
    }
}
