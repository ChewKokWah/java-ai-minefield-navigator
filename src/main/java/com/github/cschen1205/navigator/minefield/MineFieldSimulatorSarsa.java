package com.github.cschen1205.navigator.minefield;

import com.github.cschen1205.falcon.FalconConfig;
import com.github.cschen1205.falcon.TDMethod;
import com.github.cschen1205.navigator.minefield.agents.FalconNavAgent;
import com.github.cschen1205.navigator.minefield.agents.TDFalconNavAgent;

/**
 * Created by cschen1205 on 10/1/2015 0001.
 */
public class MineFieldSimulatorSarsa extends MineFieldSimulator {

    public MineFieldSimulatorSarsa(MineFieldSimulatorConfig config, FalconConfig falconConfig){
        super(config, falconConfig);
    }

    @Override
    protected FalconNavAgent createAgent(int agentId){
        int numSonarInput = config.numSonarInput;
        int numAVSonarInput = config.numAVSonarInput;
        int numBearingInput = config.numBearingInput;
        int numRangeInput = config.numRangeInput;

        TDFalconNavAgent newAgent = new TDFalconNavAgent(falconConfig, agentId,TDMethod.Sarsa, numSonarInput, numAVSonarInput, numBearingInput, numRangeInput);
        newAgent.useImmediateRewardAsQ = false;

        if(config.isImmediateRewardProvided()){
            newAgent.setQGamma(0.5);
        } else {
            newAgent.setQGamma(0.9);
        }

        return newAgent;
    }

    public static void main(String[] args){
        MineFieldSimulatorConfig config = new MineFieldSimulatorConfig();
        config.setImmediateRewardProvided(false);
        config.setNumAgents(1);

        FalconConfig falconConfig = new FalconConfig();
        falconConfig.numAction = FalconNavAgent.numAction;
        falconConfig.numState = config.numState();
        falconConfig.numReward = 2;

        MineFieldSimulatorSarsa simulator = new MineFieldSimulatorSarsa(config, falconConfig);
        simulator.runSims();
    }
}
