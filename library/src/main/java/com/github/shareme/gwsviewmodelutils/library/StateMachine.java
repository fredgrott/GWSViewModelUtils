/*
 * Copyright 2014 Dorian Cussen
 * Modifications Copyright(C) 2015 Fred Grott(GrottWorkShop)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.shareme.gwsviewmodelutils.library;

/**
 * StateMachine modified from Dynamo as the original author did not know what
 * powerful thing he had.
 *
 * The original author had coupled this in the Dynamo class to one specific
 * Observable implementation, the plain old java one. Nah, let's couple it
 * to either databinding or RxJava for something more powerful.
 *
 * Created by fgrott on 11/1/2015.
 */
@SuppressWarnings("unused")
public class StateMachine<T extends StateMachine.State> {

    private T mCurrentState;

    public synchronized void nextState(T nextState){
        if(null != mCurrentState)
            mCurrentState.exitingState();

        mCurrentState = nextState;
        nextState.enteringState();
    }

    /**
     * Convenience method. This can be called when you are finishing with the state machine to trigger any cleanup
     * code in your last state inside the {@link StateMachine.State#exitingState()} method
     *
     * @param finalState you may want to set a final state that is just a stub so any state machine calls after this method has
     *                   called will do nothing but not throw an NPE. Can be null otherwise.
     */
    public synchronized void finish(T finalState){
        if(null != mCurrentState)
            mCurrentState.exitingState();

        if(null != finalState){
            nextState(finalState);
        }else{
            mCurrentState = null;
        }
    }

    public synchronized T getCurrentState(){
        return mCurrentState;
    }

    public static abstract class State{
        public void enteringState(){}
        public void exitingState(){}
    }


}