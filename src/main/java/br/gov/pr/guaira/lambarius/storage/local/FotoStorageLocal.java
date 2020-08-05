package br.gov.pr.guaira.lambarius.storage.local;

import org.springframework.web.multipart.MultipartFile;

import br.gov.pr.guaira.lambarius.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage {

	@Override
	public String salvar(MultipartFile file) {

		return null;
	}

	@Override
	public byte[] recuperar(String nome) {

		return null;
	}

	@Override
	public void excluirFoto(String foto) {


	}

}
