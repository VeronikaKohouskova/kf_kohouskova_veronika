package rasterize;

public class FilledLineRasterizer extends LineRasterizer {

    public FilledLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int x, y, p, xk1, xk2, dx, dy, yk1, yk2;
        dx = x2 - x1;
        dy = y2 - y1;
        x = x1;
        y = y1;
        p = 2 * dy - dx; // Pro tento algoritmus zavádíme prediktor, který je odvozen z rovnic DDA algoritmu s kontrolou chyby.
        xk1 = 2 * dy;
        xk2 = 2 * (dy - dx);
        yk1 = 2 * dx;
        yk2 = 2 * (dx - dy);

        if (Math.abs(dy) < Math.abs(dx)) { //Řídící osa je x.
            if(x2 < x1) { // Prohodíme první bod s posledním, pokud je x2 menší.
                int t = x1;
                x1 = x2;
                x2 = t;
                x = x1;
                t = y1;
                y1 = y2;
                y2 = t;
                y = y1;
                dx = x2 - x1;
                dy = y2 - y1;
                p = 2 * dy - dx;
                xk1 = 2 * dy;
                xk2 = 2 * (dy - dx);
            }
            raster.setPixel(x, y, color);
            if (y1<y2) { // Pokud je y1 menší než y2, tak při posunu po ose y přičítáme.
                    while (x < x2) { // Provádíme dokud je x menší, protože řídící osa je x.
                        x = x + 1; // Vždy se posunujeme po ose x. Nezáleží na velikosti prediktoru.
                        if (p < 0) {
                            p = p + xk1; // Pokud je prediktor menší než nula, přičteme k němu k1.
                        } else {
                            p = p + xk2; // Jinak k prediktoru přičteme k2.
                            y = y + 1; // Občas se posunujeme i po ose y - pokud není prediktor menší než nula.
                        }
                        raster.setPixel(x, y, color);
                    }
                } else { // Pokud není y1 menší než y2, tak při posunu po ose y odečítáme.
                dy = Math.abs(y2 - y1);
                p = 2 * dy - dx;
                xk1 = 2 * dy;
                xk2 = 2 * (dy - dx);
                    while (x < x2) {
                        x = x + 1;
                        if (p < 0) {
                            p = p + xk1;
                        } else {
                            p = p + xk2;
                            y = y - 1;
                        }
                        raster.setPixel(x, y, color);
                    }
                }

        } else {  //Řídící osa je y. Postupujeme obdobně jako když je řídící osa x.
            if(y2 < y1) {
                int t = x1;
                x1 = x2;
                x2 = t;
                x = x1;
                t = y1;
                y1 = y2;
                y2 = t;
                y = y1;
                dx = x2 - x1;
                dy = y2 - y1;
                p = 2 * dy - dx;
                yk1 = 2 * dx;
                yk2 = 2 * (dx - dy);
            }
            raster.setPixel(x, y, color);
            if(x1<x2) {
                while (y < y2) {
                    y = y + 1;
                    if (p < 0) {
                        p = p + yk1;
                    } else {
                        p = p + yk2;
                        x = x + 1;
                    }
                    raster.setPixel(x, y, color);
                }
            } else {
                dx = Math.abs(x2 - x1);
                p = 2 * dy - dx;
                yk1 = 2 * dx;
                yk2 = 2 * (dx - dy);
                while (y < y2) {
                    y = y + 1;
                    if (p < 0) {
                        p = p + yk1;
                    } else {
                        p = p + yk2;
                        x = x - 1;
                    }
                    raster.setPixel(x, y, color);
                }

            }
        }
    }

}

/*
Bresenhamův algoritmus
Tento algoritmus se dnes používá nejčastěji, protože využívá pouze celočíselnou aritmetiku a obejde se dokonce bez dělení.
Jeho jedinou nevýhodou je, že má složitější odvození.
 */