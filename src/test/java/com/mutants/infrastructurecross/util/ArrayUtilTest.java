package com.mutants.infrastructurecross.util;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayUtilTest{
    @Test
    public void shouldReturnArrayOrderVertical(){
        assertArrayEquals(new String[] {
                        "ACTACT",
                        "TATGCC",
                        "GGAACA",
                        "CTTACC",
                        "GGGGTT",
                        "ACTGAG"},
                ArrayUtil.orderVerticalArray(new String[] {
                        "ATGCGA",
                        "CAGTGC",
                        "TTATGT",
                        "AGAAGG",
                        "CCCCTA",
                        "TCACTG"}));
    }

    @Test
    public void shouldReturnArrayOrderDiagonal(){
        assertArrayEquals(new String[] {
                        "T",
                        "CC",
                        "ACA",
                        "TGCC",
                        "CTACT",
                        "AAAATG"},
                ArrayUtil.orderDiagonalArray(new String []{
                        "ACTACT",
                        "TATGCC",
                        "GGAACA",
                        "CTTACC",
                        "GGGGTT",
                        "ACTGAG"}));
    }
}