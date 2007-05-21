/*
 * DesiredMaximizationFitness.java
 *
 * Created on Feb 20, 2007
 *
 * Copyright (C) 2007 - CIRG@UP
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package net.sourceforge.cilib.bioinf.sequencealignment;

import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.stoppingcondition.StoppingCondition;

/**
 * This class serves as a crude implementation of a stopping condition that
 * forces the optimizer to stop whenever a arbitrary fitness has been reached. Handy to measure how fast an
 * optimizer gets to a goal fitness. Prevents wasting computational power and time when optimal solution has already been found. 
 * 
 * @author Fabien Zablocki
 */
public class DesiredMaximizationFitness implements StoppingCondition {

	private Algorithm algorithm;
	private double desiredFitness;
	private int quitAnywayAfterIterations;
	
	public DesiredMaximizationFitness()
	{
	}
	
	public DesiredMaximizationFitness (DesiredMaximizationFitness copy)
	{
		this.algorithm = copy.algorithm;
		this.desiredFitness = copy.desiredFitness;
	}
	
	public DesiredMaximizationFitness clone()
	{
		return new DesiredMaximizationFitness(this);
	}
	
	public double getFitness() {
		return desiredFitness;
	}

	public void setDesiredFitness(double desiredFitness) 
	{
		this.desiredFitness = desiredFitness;
	}

	public double getPercentageCompleted()
	{
		return algorithm.getBestSolution().getFitness().getValue() / desiredFitness;
	}
	
	public boolean isCompleted()
	{
		if(algorithm.getIterations() == quitAnywayAfterIterations) return true;
		else return algorithm.getBestSolution().getFitness().getValue() >= desiredFitness;
	}
	
	public void setAlgorithm(Algorithm algorithm)
	{
		 this.algorithm = algorithm;
	}

	public int getQuitAnywayAfterIterations()
	{
		return quitAnywayAfterIterations;
	}

	public void setQuitAnywayAfterIterations(int quitAnywayAfterIterations) 
	{
		this.quitAnywayAfterIterations = quitAnywayAfterIterations;
	}
}