package rasterize;

public class DashAndDotLineRasterizer extends LineRasterizer {

    public DashAndDotLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int err, dx, dy;
        int a = 0;
        err = 0; //Zavedeme si kontrolu "chyby". V algoritmu pak budeme postupovat podle toho, jaká je její hodnota.

        // Pokud je x1 větší než x2, prohodíme první bod s posledním. Hezčí by bylo si zavést samostatnou metodu.
        if (x1>x2) {
            int t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }
        dx = x2 - x1;
        dy = y2 - y1;
        // Nadcházejícím "if" řešíme situaci, kdy je y1 větší než y2.
        if (dy<0) {
            do {
                // Nadcházející část řeší čerchování.
                if(a < 5 || a >= 15 && a <= 30) {
                    a++;
                    raster.setPixel(x1, y1, color);

                }else if (a < 15 || a < 40 && a > 30) {
                    a++;
                } else {
                    a=0;
                }
                if (err > 0) { // Pokud je chyba větší než nula, tak se posuneme po ose x a od chyby odečteme dy.
                    x1 = x1 + 1;
                    err = err - Math.abs(dy); //Nechceme, aby bylo dy záporné.
                } else { // Jinak se posunujeme po ose y a k chybě přičteme dx.
                    y1 = y1 - 1; //y2 je menší, takže od y1 odčítáme.
                    err = err + dx;
                }
            } while (x1 <= x2 && y2 <= y1);

        } else { //Podobný postup, ale tentokrát není y1 větší než y2.
            do {
                if(a < 5 || a >= 15 && a <= 30) {
                    a++;
                    raster.setPixel(x1, y1, color);

                }else if (a < 15 || a < 40 && a > 30) {
                    a++;
                } else {
                    a=0;
                }
                if (err > 0) {
                    x1 = x1 + 1;
                    err = err - dy;
                } else {
                    y1 = y1 + 1; //Když y2 není menší než y1, tak přičítáme.
                    err = err + dx;
                }
            } while (x1 <= x2 && y1 <= y2);
        }
    }
}

/*
Kvadrantní DDA algoritmus s kontrolou chyby
DDA algoritmus byl jednou z prvních implementací, která se objevila.
Jde o jednoduchý algoritmus, který by asi vymyslel každý z nás.
Pokud není rychlost kritickým aspektem aplikace, lze jej použít.
Tato konkrétní varianta algoritmu dokonce používá pouze celočíselnou aritmetiku.
V jednom kroku se postupuje o 1 pixel pouze ve směru x nebo y (ne v diagonálním směru).
Na základě chyby se určí kterým směrem jsme se vzdálili od úsečky.
 */
