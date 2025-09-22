package edu.ccrm.service.interfaces;

import java.util.List;
/**
 * A generic interface for searchable services. [cite: 69]
 */
public interface Searchable<T, R> {
    List<T> search(R criteria);
}