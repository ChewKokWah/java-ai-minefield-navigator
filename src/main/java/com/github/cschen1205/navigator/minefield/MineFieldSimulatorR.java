package com.github.cschen1205.navigator.minefield;

import com.github.cschen1205.falcon.FalconConfig;
import com.github.cschen1205.navigator.minefield.agents.FalconNavAgent;
import com.github.cschen1205.navigator.minefield.agents.RFalconNavAgent;

/**
 * Created by cschen1205 on 10/1/2015 0001.
 */
public class MineFieldSimulatorR extends MineFieldSimulator {

    @Override
    protected FalconNavAgent createAgent(int agentId){
        int numSonarInput = config.numSonarInput;
        int numAVSonarInput = config.numAVSonarInput;
        int numBearingInput = config.numBearingInput;
        int numRangeInput = config.numRangeInput;

        return new RFalconNavAgent(falconConfig, agentId, numSonarInput, numAVSonarInput, numBearingInput, numRangeInput);
    }

    public MineFieldSimulatorR(MineFieldSimulatorConfig config, FalconConfig falconConfig){
        super(config, falconConfig);
    }

    public static void main(String[] args){
        MineFieldSimulatorConfig config = new MineFieldSimulatorConfig();
        config.setImmediateRewardProvided(true);
        config.setNumAgents(1);

        FalconConfig falconConfig = new FalconConfig();
        falconConfig.numAction = FalconNavAgent.numAction;
        falconConfig.numState = config.numState();
        falconConfig.numReward = 2;

        MineFieldSimulatorR simulator = new MineFieldSimulatorR(config, falconConfig);
        simulator.runSims();
    }
}
