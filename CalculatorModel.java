package edu.sjsu.cs151.spr2018.javafxlab.model;

import java.util.ArrayList;

import edu.sjsu.cs151.spr2018.javafxlab.ModelListener;

public class CalculatorModel
{
	
	// TODO(you): Add a collection of ModelListeners
	private ArrayList<ModelListener> listeners = new ArrayList<ModelListener>();

	// state information
	boolean firstNumber;
	int op;
	boolean firstHasDecimal;
	boolean secondHasDecimal;
	boolean start;
	boolean secondNumber;
	
	private int op1;
	private int op2;
	private double op1D;
	private double op2D;
	private String result;
	private String operator;
	private String inputString;
	public CalculatorModel()
	{
		this.firstNumber = true;
		this.secondNumber = true;
		this.op = 0;
		this.firstHasDecimal = false;
		this.secondHasDecimal = false;
		this.start = false;
		this.op1 = 0;
		this.op2 = 0;
		this.op1D = 0;
		this.op2D = 0;
		this.result = "";
		this.operator = "";
		this.inputString = "";
	}
	
	// TODO(you): Create a ModelListener interface with just one function update()

	// and uncomment the following.
	// HINT: It would be useful for update to take a double as the parameter for update() 
	// so you can pass the result 
	
	public void attach(ModelListener listener) 
	{
		listeners.add(listener);
	}
	
	
	private void updateAll() 
	{
		//TODO(you): Iterate through the listener collection and call update on each of them.
		// Use the for each iteration. 

		for(ModelListener listener: listeners)
		{
			listener.update(result);
		}
	}
	
	public void handleInput(String text) 
	{
		switch(text) 
		{
			case "+": 
			case "-": 
			case "/": 
			case "*": 
			case "%":
				op ++;
				if(op > 0)
				{
					secondNumber = true;
				}
				this.operator = text;
				firstNumber = false;
				break;
			case "=":
				if (op > 0) 
				{
					result = calculate();
					firstNumber = true;
					inputString = result;
				}
				else if(op > 1)
				{
					secondNumber = true;
				}
				break;
			case ".":
				if(firstNumber)
				{
					firstHasDecimal = true;
				}
				else
				{
					secondHasDecimal = true;
				}
				inputString += text; 
				break;
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "0":
				this.start = true;
				if(!firstNumber && secondNumber)
				{
					secondNumber = false;
					inputString = "";
				}
				inputString += text;
				break;
			case "AC": 
				rest();
				break;
			case "±": 
				throw new UnsupportedOperationException("Not implemented yet");			
			default: 
				inputString = inputString + text;
				
		}
		if(start)
		{
			if(firstNumber) 
			{ 
				if(!firstHasDecimal && !operator.equals("/"))
				{
					this.op1 = Integer.parseInt(inputString);
				}
				else
				{
					if(!inputString.endsWith("."))
						this.op1D = Double.parseDouble(inputString);					
				}
				System.out.println("this is test for op1");			
			} 
			else 
			{
				if(!secondHasDecimal && !firstHasDecimal)
					this.op2 = Integer.parseInt(inputString);
				else
				{
					if(!inputString.endsWith("."))
						this.op2D = Double.parseDouble(inputString);
				}
				System.out.println("this is test for op2");			
			}
		}

		result = inputString;	
		//test
		System.out.println(result);			
		updateAll();

	}
	private void rest()
	{
		result = "";
		inputString = "";
		op = 0;
		firstNumber = true;
		operator = "";
		op1 = 0;
		op2 = 0;
		op1D = 0;
		op2D = 0;
		firstHasDecimal = false;
		secondHasDecimal = false;
		start = false;
	}

	private String calculate() 
	{
		double res = 0;
		switch(this.operator) 
		{
			case "+":
				if(!firstHasDecimal && !secondHasDecimal)
				{
					return (op1 + op2) + ""; 
				}
				else if(firstHasDecimal && !secondHasDecimal)
				{
					res = op1D + op2;
				}				
				else if(!firstHasDecimal && secondHasDecimal)
				{
					res = op1 + op2D;
				}				
				else if(firstHasDecimal && secondHasDecimal)
				{
					res = op1D + op2D;
				}				
				break;
			case "-":  
				if(!firstHasDecimal && !secondHasDecimal)
				{
					return (op1 - op2) + ""; 
				}
				else if(firstHasDecimal && !secondHasDecimal)
				{
					res = op1D - op2;
				}				
				else if(!firstHasDecimal && secondHasDecimal)
				{
					res = op1 - op2D;
				}				
				else if(firstHasDecimal && secondHasDecimal)
				{
					res = op1D - op2D;
				}						
				break;
			case "/":  
				if(!firstHasDecimal && !secondHasDecimal)
				{
					return ((double)op1 / op2) + ""; 
				}
				else if(firstHasDecimal && !secondHasDecimal)
				{
					res = op1D / op2;
				}				
				else if(!firstHasDecimal && secondHasDecimal)
				{
					res = op1 / op2D;
				}				
				else if(firstHasDecimal && secondHasDecimal)
				{
					res = op1D / op2D;
				}							
				break;
			case "*": 
				if(!firstHasDecimal && !secondHasDecimal)
				{
					return (op1 * op2) + ""; 
				}
				else if(firstHasDecimal && !secondHasDecimal)
				{
					res = op1D * op2;
				}				
				else if(!firstHasDecimal && secondHasDecimal)
				{
					res = op1 * op2D;
				}				
				else if(firstHasDecimal && secondHasDecimal)
				{
					res = op1D * op2D;
				}							
				break;
			case "%":  
				if(!firstHasDecimal && !secondHasDecimal)
				{
					return (op1 % op2) + ""; 
				}
				else if(firstHasDecimal && !secondHasDecimal)
				{
					res = op1D % op2;
				}				
				else if(!firstHasDecimal && secondHasDecimal)
				{
					res = op1 % op2D;
				}				
				else if(firstHasDecimal && secondHasDecimal)
				{
					res = op1D % op2D;
				}							
				break;
		}
		
		// TODO(you): implement the switch case for calculation.
		return res + "";
	}
	

	public String getResult() 
	{	
		return result;
	}

}
