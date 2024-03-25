package Service;

import Model.Polinom;
import Model.Result;

public interface IOperationsService {
    Polinom aduna(Polinom p1, Polinom p2);
    Polinom scadere(Polinom p1, Polinom p2);
    Polinom inmultire(Polinom p1, Polinom p2);
    Result impartire(Polinom p1, Polinom p2);
    Polinom derivare(Polinom p1);
    Polinom integrare(Polinom p1);
}
