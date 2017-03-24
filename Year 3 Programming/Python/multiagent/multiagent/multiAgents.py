# multiAgents.py
# --------------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

from util import manhattanDistance
from game import Directions
import random, util

from game import Agent

class ReflexAgent(Agent):
  """
    A reflex agent chooses an action at each choice point by examining
    its alternatives via a state evaluation function.

    The code below is provided as a guide.  You are welcome to change
    it in any way you see fit, so long as you don't touch our method
    headers.
  """


  def getAction(self, gameState):
    """
    You do not need to change this method, but you're welcome to.

    getAction chooses among the best options according to the evaluation function.

    Just like in the previous project, getAction takes a GameState and returns
    some Directions.X for some X in the set {North, South, West, East, Stop}
    """
    # Collect legal moves and successor states
    legalMoves = gameState.getLegalActions()

    # Choose one of the best actions
    scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
    bestScore = max(scores)
    bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
    chosenIndex = random.choice(bestIndices) # Pick randomly among the best

    "Add more of your code here if you want to"

    return legalMoves[chosenIndex]

  def evaluationFunction(self, currentGameState, action):
    """
    Design a better evaluation function here.

    The evaluation function takes in the current and proposed successor
    GameStates (pacman.py) and returns a number, where higher numbers are better.

    The code below extracts some useful information from the state, like the
    remaining food (oldFood) and Pacman position after moving (newPos).
    newScaredTimes holds the number of moves that each ghost will remain
    scared because of Pacman having eaten a power pellet.

    Print out these variables to see what you're getting, then combine them
    to create a masterful evaluation function.
    """
    # Useful information you can extract from a GameState (pacman.py)
    successorGameState = currentGameState.generatePacmanSuccessor(action)
    newPos = successorGameState.getPacmanPosition()
    oldFood = currentGameState.getFood()
    newGhostStates = successorGameState.getGhostStates()
    newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

    "*** YOUR CODE HERE ***"
    listOfFood = oldFood.asList()
    listOfGhosts = successorGameState.getGhostPositions()

    for ghosts in listOfGhosts:
	if (util.manhattanDistance(newPos, ghosts) < 2 ):
		return -100000000

    for food in listOfFood:
	if(util.manhattanDistance(newPos, food) == 0):
		return 1000000000

    if (successorGameState.getPacmanPosition() == currentGameState.getPacmanPosition()):
	return -10000000

    summ = 1000000
    
    for food in listOfFood:
	summ = summ - (util.manhattanDistance(food, newPos) * 100)

    for ghosts in listOfGhosts:
	summ = summ + (util.manhattanDistance(ghosts, newPos) * 99 * len(listOfFood))

    

    return summ

    return successorGameState.getScore()

def scoreEvaluationFunction(currentGameState):
  """
    This default evaluation function just returns the score of the state.
    The score is the same one displayed in the Pacman GUI.

    This evaluation function is meant for use with adversarial search agents
    (not reflex agents).
  """
  return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
  """
    This class provides some common elements to all of your
    multi-agent searchers.  Any methods defined here will be available
    to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

    You *do not* need to make any changes here, but you can if you want to
    add functionality to all your adversarial search agents.  Please do not
    remove anything, however.

    Note: this is an abstract class: one that should not be instantiated.  It's
    only partially specified, and designed to be extended.  Agent (game.py)
    is another abstract class.
  """

  def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
    self.index = 0 # Pacman is always agent index 0
    self.evaluationFunction = util.lookup(evalFn, globals())
    self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
  """
    Your minimax agent (question 2)
  """

  def getAction(self, gameState):
    """
      Returns the minimax action from the current gameState using self.depth
      and self.evaluationFunction.

      Here are some method calls that might be useful when implementing minimax.

      gameState.getLegalActions(agentIndex):
        Returns a list of legal actions for an agent
        agentIndex=0 means Pacman, ghosts are >= 1

      Directions.STOP:
        The stop direction, which is always legal

      gameState.generateSuccessor(agentIndex, action):
        Returns the successor game state after an agent takes an action

      gameState.getNumAgents():
        Returns the total number of agents in the game
    """
    "*** YOUR CODE HERE ***"
    # The max function will choose the best move for pacman, or rather will tell the what score pacman gets for choosing the best move.

    def maxFunction(gameStatus, depth):
	listOfPactions = gameStatus.getLegalActions(0)
	if (depth == self.depth or gameStatus.isLose() or gameStatus.isWin()):
		return [self.evaluationFunction(gameStatus), Directions.STOP]
	maxValue = -float(9999999)
	bestAction = Directions.STOP
	if len(listOfPactions) == 0:
                return [self.evaluationFunction(gameStatus), Directions.STOP]
	if (Directions.STOP in listOfPactions):
		listOfPactions.remove(Directions.STOP)
	for actions in listOfPactions:
		newState = gameStatus.generateSuccessor(0, actions)
		newValue = minFunction(newState, depth)[0] #We choose the best action from the ghosts best actions
		if (newValue > maxValue):
			maxValue = newValue
			bestAction = actions
			
	return [maxValue, bestAction]
		
    #The minFunction will determine the ghosts best action
    def minFunction(gameStatus, depth):

	minValue = float(99999)
	bestAction = Directions.STOP
	listOfActions = gameStatus.getLegalActions(1)
	if (depth == self.depth or gameStatus.isLose() or gameStatus.isWin()):
            return [self.evaluationFunction(gameStatus), Directions.STOP]
	
	for actions in listOfActions:
		newState = gameStatus.generateSuccessor(1, actions)
		newValue = maxFunction(newState, depth + 1)[0] #we increase the depth and determine what the max moves next best action would gain.
		if (newValue < minValue):
			minValue = newValue
			bestAction = actions
	return [minValue, bestAction]

    result = maxFunction(gameState, 0)
    return result[1]
    util.raiseNotDefined()

class AlphaBetaAgent(MultiAgentSearchAgent):
  """
    Your minimax agent with alpha-beta pruning (question 3)
  """

  def getAction(self, gameState):
    """
      Returns the minimax action using self.depth and self.evaluationFunction
    """
    "*** YOUR CODE HERE ***"
    #Basically the same as maxFunction for minimax, but there's alpha beta pruning.
    def maxFunction(gameStatus, depth, alpha, beta):
	listOfPactions = gameStatus.getLegalActions(0)
	if (depth == self.depth or gameStatus.isLose() or gameStatus.isWin()):
		return [self.evaluationFunction(gameStatus), Directions.STOP]
	maxValue = -float(9999999)
	bestAction = Directions.STOP
	if len(listOfPactions) == 0:
                return [self.evaluationFunction(gameStatus), Directions.STOP]
	if (Directions.STOP in listOfPactions):
		listOfPactions.remove(Directions.STOP)
	for actions in listOfPactions:
		newState = gameStatus.generateSuccessor(0, actions)
		newValue = minFunction(newState, depth, alpha, beta)[0]
		if (newValue > maxValue):
			maxValue = newValue
			bestAction = actions
		if (maxValue >= beta):#this is where the alphabeta pruning happens
			return [maxValue, bestAction]
		alpha = max(alpha, maxValue)
			
	return [maxValue, bestAction]
		
    #minfunction with alpha beta pruning.
    def minFunction(gameStatus, depth, alpha, beta):

	minValue = float(9999999)
	bestAction = Directions.STOP
	listOfActions = gameStatus.getLegalActions(1)
	if (depth == self.depth or gameStatus.isLose() or gameStatus.isWin()):
            return [self.evaluationFunction(gameStatus), Directions.STOP]
	
	for actions in listOfActions:
		newState = gameStatus.generateSuccessor(1, actions)
		newValue = maxFunction(newState, depth + 1, alpha, beta)[0]
		if (newValue < minValue):
			minValue = newValue
			bestAction = actions
		if (minValue <= alpha):#this is where the pruning happens
			return [minValue, bestAction]
		beta = min (beta, minValue)
	return [minValue, bestAction]

    result = maxFunction(gameState, 0, -9999999, 9999999)
    return result[1]
    util.raiseNotDefined()

class ExpectimaxAgent(MultiAgentSearchAgent):
  """
    Your expectimax agent (question 4)
  """

  def getAction(self, gameState):
    """
      Returns the expectimax action using self.depth and self.evaluationFunction

      All ghosts should be modeled as choosing uniformly at random from their
      legal moves.
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

def betterEvaluationFunction(currentGameState):
  """
    Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
    evaluation function (question 5).

    DESCRIPTION: <write something here so we know what you did>
  """
  "*** YOUR CODE HERE ***"
  util.raiseNotDefined()

# Abbreviation
better = betterEvaluationFunction

class ContestAgent(MultiAgentSearchAgent):
  """
    Your agent for the mini-contest
  """

  def getAction(self, gameState):
    """
      Returns an action.  You can use any method you want and search to any depth you want.
      Just remember that the mini-contest is timed, so you have to trade off speed and computation.

      Ghosts don't behave randomly anymore, but they aren't perfect either -- they'll usually
      just make a beeline straight towards Pacman (or away from him if they're scared!)
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

