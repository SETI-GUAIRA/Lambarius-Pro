package br.gov.pr.guaira.lambarius.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

  public final String THUMBNAIL_PREFIX = "thumbnail.";

  public String salvar(MultipartFile file);

	public byte[] recuperar(String nome);

	public void excluirFoto(String foto);
}
