package rasterize;

public class DottedLineRasterizer extends LineRasterizer {

    public DottedLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        float k = (y2 - y1) / (float) (x2 - x1);
        float q = y1 - k * x1;
        int a = 0; // kvůli tečkování

        if (k<1 && k>-1) { //Pokud je směrnice menší než 1 a větší než -1, bude řídící osou x.
            if(x2<x1) { //Pokud je x2 menší, tak prohodíme první bod s posledním.
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
        for (int x = x1; x <= x2; x++) { //Provádíme dokud x se nerovná x2.
            float y = k * x + q;
            a++;
            if (a%5 == 0) { // vykreslujeme pouze pokud je zbytek po dělení "a" pěti nulový.
                raster.setPixel(x, Math.round(y), color);
            }
            }
        } else { // V ostatních případech je řídící osou y.
            if(y2<y1) { //Pokud je y2 menší, tak prohodíme první bod s posledním.
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
            for (int y = y1; y<=y2; y++) { //Provádíme dokud y se nerovná y2.
                float x = (y - q) / k;
                a++;
                if (a%5 == 0) { // vykreslujeme pouze pokud je zbytek po dělení "a" pěti nulový.
                    raster.setPixel(Math.round(x),y, color);
                }
            }
        }
    }
}

/*
Triviální algoritmus
Vychází z explictního vyjádření funkce přímky, takže by na tento algoritmus přišla většina lidí.
Je použitelný i pro složitější křivky.
Bohužel se příliš nevyužívá, protože obsahuje násobení a sčítání v plovoucí řádové čárce.
 */
