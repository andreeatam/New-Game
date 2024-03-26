package Controller;

import Model.Polinom;
import Model.Result;
import Service.OperationsService;

public class MathController {
    private final OperationsService operationsService;

    public MathController() {
        this.operationsService = new OperationsService();
    }

    public Polinom adunare(Polinom polinom1, Polinom polinom2) {
        return operationsService.aduna(polinom1, polinom2);
    }

    public Polinom scadere(Polinom polinom1, Polinom polinom2) {
        return operationsService.scadere(polinom1, polinom2);
    }

    public Polinom inmultire(Polinom polinom1, Polinom polinom2) {
        return operationsService.inmultire(polinom1, polinom2);
    }
    public Result impartire(Polinom polinom1, Polinom polinom2) {
        return operationsService.impartire(polinom1, polinom2);
    }
    public Polinom derivare(Polinom polinom) {
        return operationsService.derivare(polinom);
    }

    public Polinom integrare(Polinom polinom) {
        return operationsService.integrare(polinom);
    }
}
