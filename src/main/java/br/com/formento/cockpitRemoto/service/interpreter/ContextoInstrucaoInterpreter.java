package br.com.formento.cockpitRemoto.service.interpreter;

public class ContextoInstrucaoInterpreter implements ContextoInterpreter<String, ResultadoInterpreterInstrucao> {

	private final String input;
	private ResultadoInterpreterInstrucao output;

	public ContextoInstrucaoInterpreter(String input, ResultadoInterpreterInstrucao output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public String getInput() {
		return input;
	}

	@Override
	public ResultadoInterpreterInstrucao getOutput() {
		return output;
	}

}
