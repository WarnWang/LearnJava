package dsaaij;

import java.util.Stack;

/**
 * Created by warn on 7/2/2016.
 */
public class HanoiTower {
    Peg from, buffer, to;
    int size;

    public HanoiTower(int size) {
        this.size = size;
        from = new Peg(size);
        buffer = new Peg(size);
        to = new Peg(size);
        from.initPeg();
    }

    public int getSize() {
        return size;
    }

    public void displayHanoiTower() {
        System.out.println("from: " + from.toString());
        System.out.println("buffer: " + buffer.toString());
        System.out.println("to: " + to.toString());
    }

    static private void solution(int n, Peg from, Peg to, Peg buffer, HanoiTower tower) {
        if (n == 1) {
            int disk = from.popOneDisk();
            to.addOneDisk(disk);
            tower.displayHanoiTower();
        } else {
            solution(n - 1, from, buffer, to, tower);
            int disk = from.popOneDisk();
            to.addOneDisk(disk);
            tower.displayHanoiTower();
            solution(n - 1, buffer, to, from, tower);
        }
    }

    static public void solveHannoi(HanoiTower tower) {
        solution(tower.size, tower.from, tower.to, tower.buffer, tower);
        tower.displayHanoiTower();
    }

    public static void main(String[] args) {
        // put your codes here
        HanoiTower test = new HanoiTower(5);
        solveHannoi(test);
    }
}

class Peg {
    Stack<Integer> peg = new Stack<>();
    int size;

    Peg(int size) {
        this.size = size;
    }

    public void initPeg() {
        for (int i = size; i > 0; i--) {
            peg.push(i);
        }
    }

    public boolean addOneDisk(int diskSize) {
        if (peg.size() >= this.size) return false;
        else if (peg.size() == 0) {
            peg.push(diskSize);
            return true;
        }
        int currentHead = peg.pop();
        peg.push(currentHead);
        if (currentHead > diskSize) {
            peg.push(diskSize);
            return true;
        } else {
            return false;
        }
    }

    public int popOneDisk() {
        return peg.pop();
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        return peg.toString();
    }

    public int getMax() {
        return peg.size() > 0 ? peg.firstElement() : 0;
    }
}