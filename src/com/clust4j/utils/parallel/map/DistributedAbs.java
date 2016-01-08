package com.clust4j.utils.parallel.map;

import org.apache.commons.math3.util.FastMath;

import com.clust4j.utils.VecUtils;

/**
 * A class for distributed summing of vectors
 * @author Taylor G Smith
 */
final public class DistributedAbs extends MapTaskOperator {
	private static final long serialVersionUID = -6086182277529660733L;

    private DistributedAbs(final double[] arr, int lo, int hi) {
        super(arr, lo, hi);
    }

    @Override
    protected double operate(final double a) {
    	return FastMath.abs(a);
    }
	
	public static double[] operate(final double[] array) {
		return getThreadPool().invoke(new DistributedAbs(VecUtils.copy(array), 0, array.length));
    }
	
	@Override
	protected DistributedAbs newInstance(final double[] array, final int low, final int high) {
		return new DistributedAbs(array, low, high);
	}
}