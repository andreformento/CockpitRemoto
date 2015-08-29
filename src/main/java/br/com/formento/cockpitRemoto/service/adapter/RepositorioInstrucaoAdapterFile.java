package br.com.formento.cockpitRemoto.service.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioInstrucaoAdapterFile implements RepositorioInstrucaoTarget {

	private File file;
	private BufferedReader bufferedReader;
	private String nomeArquivo;

	public RepositorioInstrucaoAdapterFile(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public void abrir() {
		file = new File(nomeArquivo);
		bufferedReader = null;
	}

	@Override
	public List<String> ler() {
		List<String> result = new ArrayList<>();
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String linha;
			while ((linha = bufferedReader.readLine()) != null)
				result.add(linha);
		} catch (IOException e) {
			e.printStackTrace();
			result.add(e.getMessage());
		}

		return result;
	}

	@Override
	public void fechar() {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
