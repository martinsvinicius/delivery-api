package tech.blobteam.algafood.exception;

/** @author vinicius-victor */
public class EntityInUseException extends RuntimeException {
  public EntityInUseException(String msg) {
    super(msg);
  }
}
