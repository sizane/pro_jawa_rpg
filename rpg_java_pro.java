import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class character() {
    String name;
    int level;
    int exp;
    int batasExp;
    int MaxHP;
    int HPnow;
    int attack;
    int mana;
    int deff;

    public character(String namaInput) {
        this.name = namaInput;
        this.MaxHP = 100;
        this.HPnow = 100;
        this.exp = 0;
        this.batasExp = 100;
        this.attack = 10;
        this.mana = 0;
        this.deff = 5;
    }
    public void tambahExp(int expDidapat) {
        this.exp = this.exp + expDidapat;
        System.out.println(this.name + " mendapat " + expDidapat + " EXP");
        if (this.exp >= this.batasExp) {
            naikLevel();
        }
    }

    public void naikLevel() {
        this.level = this.level + 1;
        this.exp = this.exp - this.batasExp;
        this.batasExp = this.batasExp + 50;
        this.MaxHP = this.MaxHP + 20;
        this.HPnow = this.MaxHP;
        this.mana = this.mana + 2;
        this.attack = this.attack + 5;
        this.deff = this.deff + 3;
        System.out.println("Level naik menjadi " + this.level);
    }
}

public class SaveLoadSystem {
    public void saveGame(int slot, character p, Inventory inv) {
        if (slot < 1 || slot > 4) {
            System.out.println("Pilih slot 1, 2, 3, atau 4!");
            return;
        }
        try {
            FileWriter writer = new FileWriter("save_slot_" + slot + ".txt");
            
            writer.write(p.name + "," + p.level + "," + p.exp + "," + p.batasExp + "," + p.HPnow + "," + p.MaxHP + "," + p.attack + "," + p.mana + "," + p.deff + "\n");
            
            String gabunganItem = String.join(",", inv.tas);
            writer.write(gabunganItem + "\n");
            
            writer.write(inv.senjataAktif + "," + inv.bonusStat + "\n");
            
            writer.close();
            System.out.println("Game berhasil disimpan di Slot " + slot);
        } catch (Exception e) {
            System.out.println("Gagal menyimpan game");
        }
    }

    public void loadGame(int slot, character p, Inventory inv) {
        try {
            File file = new File("save_slot_" + slot + ".txt");
            Scanner reader = new Scanner(file);
            
            if (reader.hasNextLine()) {
                String dataKarakter = reader.nextLine();
                String[] stats = dataKarakter.split(",");
                
                p.name = stats[0];
                p.level = Integer.parseInt(stats[1]);
                p.exp = Integer.parseInt(stats[2]);
                p.batasExp = Integer.parseInt(stats[3]);
                p.HPnow = Integer.parseInt(stats[4]);
                p.MaxHP = Integer.parseInt(stats[5]);
                p.attack = Integer.parseInt(stats[6]);
                p.mana = Integer.parseInt(stats[7]);
                p.deff = Integer.parseInt(stats[8]);
            }
            
            if (reader.hasNextLine()) {
                String dataInventory = reader.nextLine();
                inv.tas.clear(); 
                if (!dataInventory.isEmpty()) {
                    String[] items = dataInventory.split(",");
                    inv.tas.addAll(Arrays.asList(items));
                }
            }

            if (reader.hasNextLine()) {
                String dataEquip = reader.nextLine();
                String[] equip = dataEquip.split(",");
                inv.senjataAktif = equip[0];
                inv.bonusStat = Integer.parseInt(equip[1]);
            }
            
            System.out.println("Berhasil memuat data dari Slot " + slot);
            reader.close();
        } catch (Exception e) {
            System.out.println("Slot " + slot + " kosong atau gagal dimuat");
        }
    }
}
public class Inventory {
    public ArrayList<String> tas = new ArrayList<>();
    public String senjataAktif = "Kosong";
    public int bonusStat = 0;

    public void tambahItem(String namaItem) {
        tas.add(namaItem);
        System.out.println("Kamu mendapatkan " + namaItem);
    }

    public void lepasSenjata() {
    if (this.senjataAktif.equals("Kosong")) {
        System.out.println("Kamu tidak sedang memakai senjata");
    } else {
        System.out.println("Kamu melepas senjata " + this.senjataAktif);
        tas.add(this.senjataAktif);
        this.senjataAktif = "Kosong";
        this.bonusStat = 0;
        System.out.println("Senjata kembali ke dalam tas");
    }
}

public void pakaiSenjata(String namaSenjata, int statDiberikan) {
    if (tas.contains(namaSenjata)) {
        if (!this.senjataAktif.equals("Kosong")) {
            lepasSenjata();
        }
        this.senjataAktif = namaSenjata;
        this.bonusStat = statDiberikan;
        tas.remove(namaSenjata);
        System.out.println("Kamu memakai senjata " + namaSenjata);
    } else {
        System.out.println("Senjata tidak ada di dalam tas");
    }
}
}

public class battle() {
    

    
}
public class rpg_java_pro {
    public static void main(String[] args) {

    }
}