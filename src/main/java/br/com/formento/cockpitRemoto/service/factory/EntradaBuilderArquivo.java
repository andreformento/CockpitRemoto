package br.com.formento.cockpitRemoto.service.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.formento.cockpitRemoto.model.EntradaImpl;

public class EntradaBuilderArquivo extends EntradaAbstractBuilder {

	private File file;

	public EntradaBuilderArquivo(File file) {
		this.file = file;
	}

	@Override
	public void buildComandoList() {
		comandoList = new ArrayList<>();
		try {
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
				String linha;
				while ((linha = bufferedReader.readLine()) != null)
					comandoList.add(linha);
			} finally {
				if (bufferedReader != null)
					bufferedReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			comandoList.add(e.getMessage());
		}
	}

	@Override
	public void buildInstance() {
		product = new EntradaImpl(comandoList);
	}

}
