//
// Just a basic example taken from the docs and modified slightly.
//

import { Configuration, OpenAIApi } from "openai";

const configuration = new Configuration({
  apiKey: process.env.OPENAI_API_KEY,
});

const openai = new OpenAIApi(configuration);

function generatePrompt(animal) {
  const capitalizedAnimal =
    animal[0].toUpperCase() + animal.slice(1).toLowerCase();
  return `Suggest three names for an animal that is a superhero.
  
  Animal: Cat
  Names: Captain Sharpclaw, Agent Fluffball, The Incredible Feline
  Animal: Dog
  Names: Ruff the Protector, Wonder Canine, Sir Barks-a-Lot
  Animal: ${capitalizedAnimal}
  Names:`;
}

try {
  const completion = await openai.createCompletion({
    model: "text-davinci-003",
    prompt: generatePrompt("horse"),
    temperature: 0.6,
  });
  console.log(completion.data);
} catch (error) {
  if (error.response) {
    console.error(error.response.status, error.response.data);
  } else {
    console.error(`Error with OpenAI API request: ${error.message}`);
  }
}
