package com.example.loginapp.repository;

/**
 * A generic callback interface to handle success and failure of repository operations.
 *
 * @param <T> The type of the data being returned on success.
 */
public interface RepositoryCallback<T> {

    /**
     * Called when the repository operation is successful.
     *
     * @param data The data returned from the repository operation.
     */
    void onSuccess(T data);

    /**
     * Called when the repository operation fails.
     *
     * @param message The error message describing the failure.
     */
    void onFailure(String message);
}
