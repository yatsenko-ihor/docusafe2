package de.adorsys.datasafe.types.api.actions;

import de.adorsys.datasafe.types.api.resource.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.net.URI;

/**
 * Request to list available resources at some location
 * @param <T> Location owner
 * @param <L> Path to the location
 */
@Value
@AllArgsConstructor
@Builder(toBuilder = true)
public class ListRequest<T, L extends ResourceLocation> {

    //TODO: Add ROOT bucket path constant
    @NonNull
    private final T owner;

    @NonNull
    private final L location;

    @NonNull
    private final StorageIdentifier storageIdentifier;

    private ListRequest(@NonNull T owner, @NonNull L location) {
        this.owner = owner;
        this.location = location;
        this.storageIdentifier = StorageIdentifier.DEFAULT;
    }

    public static <T> ListRequest<T, PrivateResource> forDefaultPrivate(T owner, URI path) {
        return forDefaultPrivate(owner, new Uri(path));
    }

    public static <T> ListRequest<T, PrivateResource> forDefaultPrivate(T owner, Uri path) {
        return new ListRequest<>(owner, BasePrivateResource.forPrivate(path));
    }

    public static <T> ListRequest<T, PrivateResource> forDefaultPrivate(T owner, String path) {
        return new ListRequest<>(owner, BasePrivateResource.forPrivate(new Uri(path)));
    }
}
