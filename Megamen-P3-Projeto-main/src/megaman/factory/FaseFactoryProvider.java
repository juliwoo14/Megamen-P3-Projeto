package megaman.factory;

import megaman.model.TipoFase;

public final class FaseFactoryProvider {
    private FaseFactoryProvider() {
    }

    public static FaseFactory criar(TipoFase tipo) {
        return switch (tipo) {
            case WOOD_MAN -> new WoodManFaseFactory();
            case AIR_MAN -> new AirManFaseFactory();
        };
    }
}
