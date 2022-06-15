package stackoverflow;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRsqrt {

//	static float Q_rsqrt( float number ) {
//        long i;
//        float x2, y;
//        final float threehalfs = 1.5F;
//        x2 = number * 0.5F;
//        y = number;
//        i = *(long * ) &y;	// evil floating point bit hack
//        i = 0x5f3759df - ( i >> 1 );	// what the fuck?
//        y = * (float * ) &i;
//        y = y *(threehalfs - ( x2 * y* y ) );	// 1st iteration
////		y = y *(threehalfs - ( x2 * y* y ) );	// 2nd iteration, can be removed
//        return y;
//	}

    static final float THREE_HALFS = 1.5F;
	static float Q_rsqrt( float number ) {
        float x2 = number * 0.5F;
        float y = number;
        int i = Float.floatToIntBits(y);
        i = 0x5f3759df - ( i >> 1 );	// what the fuck?
        y = Float.intBitsToFloat(i);
        y *= THREE_HALFS - x2 * y * y;	// 1st iteration
    	y *= THREE_HALFS - x2 * y * y;	// 2nd iteration, can be removed
        return y;
	}

	@Test
	public void test() {
		for (float t = 2; t < 10.0; ++t) {
            float q = Q_rsqrt(t);
            System.out.printf("1/SQRT(%f)=%f q=%f%n", t, 1 / Math.sqrt(t), q);
            assertEquals(1 / Math.sqrt(t), q, 1e-5);
		}
	}

}
