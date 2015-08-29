package br.com.formento.cockpitRemoto.service.interpreter;

public interface Interpreter<INPUT, OUTPUT> {

	void interpretar(ContextoInterpreter<INPUT, OUTPUT> contextoInterpreter);

}