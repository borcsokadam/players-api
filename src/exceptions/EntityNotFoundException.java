package src.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super(String.format("Entity not found with id: %d", id));
    }
}
