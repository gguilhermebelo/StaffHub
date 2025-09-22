package domain.upload;
public class AtestadoUploader extends Uploader {
    public AtestadoUploader(Storage storage) { super(storage); }
    protected void validar(String arquivo) {
        if (!arquivo.endsWith(".pdf") && !arquivo.endsWith(".jpg"))
            throw new IllegalArgumentException("Atestado deve ser PDF/JPG");
        System.out.println("Validado Atestado: " + arquivo);
    }
}
