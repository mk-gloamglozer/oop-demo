package com.mkdevs.utils;

import java.util.Collection;

import com.mkdevs.domain.Dice;
import com.mkdevs.domain.DiceFactory;
import com.mkdevs.domain.DiceFactoryImpl;
import com.mkdevs.io.UserIO;
import com.mkdevs.io.UserIOStdIO;
import com.mkdevs.repository.DiceRepository;
import com.mkdevs.repository.DiceRepositoryImpl;
import com.mkdevs.service.DiceInputService;
import com.mkdevs.service.DiceInputServiceStdInput;
import com.mkdevs.service.DiceModificationService;
import com.mkdevs.service.DiceModificationServiceImpl;

/**
 * Wires the app together 
 * 
 * will be replaced by spring boot in later implementation
 * @author mike
 *
 */
public class AppConstructor {
	
	private static DiceModificationService modService;
	private static DiceFactory diceFactory;
	private static DiceCalcUtil calcUtil;
	private static DiceInputService diceService;
	private static UserIO userio;
	private static DiceRepository diceRepo;
	private static FunctionCaller functionCaller;
	
	public static FunctionCaller init() {
		if(functionCaller == null) {
			functionCaller = new FunctionCaller(diceService(),calcUtil(),userio(),modService()); 
		}
		return functionCaller;
	}
	
	public static void initialiseRepoWithDice(Collection<Dice> diceCollection) {
		diceCollection.stream().forEach((dice) -> diceRepo().saveDice(dice));
	}

	private static DiceModificationService modService() {
		if(modService==null) {
			modService = new DiceModificationServiceImpl(diceFactory(), diceRepo(), userio());
		}
		return modService;
	}

	public static DiceFactory diceFactory() {
		if(diceFactory == null) {
			diceFactory = new DiceFactoryImpl();
		}
		return diceFactory;
	}

	private static DiceCalcUtil calcUtil() {
		if(calcUtil == null) {
			calcUtil =  new DiceCalcUtil() { };
		}
		return calcUtil;
	}

	private static DiceInputService diceService() {
		if(diceService == null) {
			diceService =  new DiceInputServiceStdInput(diceRepo(), userio());
		}
		return diceService;
	}

	private static UserIO userio() {
		if(userio == null) {
			userio =  new UserIOStdIO();
		}
		return userio;
	}

	private static DiceRepository diceRepo() {
		if(diceRepo==null) {
			diceRepo = new DiceRepositoryImpl();
		}
		return diceRepo;
	}
}
