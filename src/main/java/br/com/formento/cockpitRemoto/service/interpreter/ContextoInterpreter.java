package br.com.formento.cockpitRemoto.service.interpreter;

public interface ContextoInterpreter<INPUT, OUTPUT> {

	INPUT getInput();

	OUTPUT getOutput();

}
