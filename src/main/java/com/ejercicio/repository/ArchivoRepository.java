
package com.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author walter.maradiaga
 */
@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}