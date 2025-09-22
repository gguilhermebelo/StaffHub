package domain.upload;

public abstract class Uploader {
    protected final Storage storage;
    protected Uploader(Storage storage){ this.storage = storage; }

    public final String upload(String arquivoLocal){
        validar(arquivoLocal);
        return storage.salvar(arquivoLocal);
    }
    protected abstract void validar(String arquivoLocal);
}
