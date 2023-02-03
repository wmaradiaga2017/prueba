package com.ejercicio.interfaces;

/**
 *
 * @author walter.maradiaga
 */
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  public void creardirectorio(String ruta);

  public void guardararchivo(MultipartFile file,String ruta);

}