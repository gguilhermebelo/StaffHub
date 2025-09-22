package domain.upload;
public class HoleriteUploader extends Uploader {
    public HoleriteUploader(Storage storage) { super(storage); }
    protected void validar(String arquivo) {
        if (!arquivo.endsWith(".pdf"))
            throw new IllegalArgumentException("Holerite deve ser PDF");
        System.out.println("Validado Holerite: " + arquivo);
    }
}
