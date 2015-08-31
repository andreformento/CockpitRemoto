package br.com.formento.cockpitRemoto.service.templateMethod;

import java.util.ArrayList;
import java.util.List;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Cockpit;
import br.com.formento.cockpitRemoto.model.Entrada;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;

public abstract class CockpitTemplateMethodAbstract implements Cockpit {

	private final List<CenarioProcessamento> cenarioProcessamentoEstados;

	private boolean isInstanciaPreparada = false;

	private CenarioProcessamento cenarioProcessamento;

	public CockpitTemplateMethodAbstract() {
		this.cenarioProcessamentoEstados = new ArrayList<>();
	}

	protected abstract CenarioProcessamento criarCenarioProcessamento();

	protected abstract Entrada lerEntrada(EntradaBuilder entradaBuilder);

	protected abstract Resultado processarLeitura();

	protected abstract void checklistDeInstancia();

	protected abstract void checklistDeExecucao(Entrada entrada);

	private void checklistDeInstanciaInterno() {
		if (!isInstanciaPreparada)
			checklistDeInstancia();

		isInstanciaPreparada = true;
	}

	@Override
	public CenarioProcessamento getCenarioProcessamento() {
		if (cenarioProcessamento == null)
			cenarioProcessamento = criarCenarioProcessamento();

		return cenarioProcessamento;
	}

	@Override
	public final Resultado executarLote(EntradaBuilder entradaBuilder) {
		try {
			CenarioProcessamento clone = getCenarioProcessamento().clone();
			cenarioProcessamentoEstados.add(clone);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		checklistDeInstanciaInterno();

		Entrada entrada = lerEntrada(entradaBuilder);

		checklistDeExecucao(entrada);

		Resultado resultadoProcessarLeitura = processarLeitura();
		// se ocorrer algum erro no processamento ele faz um rollback no lote
		if (!resultadoProcessarLeitura.getTipoResultado().isResultadoOk())
			desfazerExecutarLoteInterno();

		return resultadoProcessarLeitura;
	}

	@Override
	public Resultado desfazerExecutarLote() {
		// validar se pode desfazer (se tem execução anterior)
		if (cenarioProcessamentoEstados.isEmpty())
			return new ResultadoImpl(TipoResultado.AVISO, "Não existe nenhuma operação para ser desfeita");
		else {
			desfazerExecutarLoteInterno();
			return new ResultadoImpl(TipoResultado.SUCESSO, "Lote de operações desfeito");
		}
	}

	private void desfazerExecutarLoteInterno() {
		if (!cenarioProcessamentoEstados.isEmpty()) {
			int index = cenarioProcessamentoEstados.size() - 1;
			cenarioProcessamento = cenarioProcessamentoEstados.remove(index);
		}
	}
}
