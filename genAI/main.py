import google.generativeai as genai
from dotenv import load_dotenv

import os

load_dotenv()  # Load variables from .env


genai.configure(api_key=os.getenv("API_KEY"))

model = genai.GenerativeModel("gemini-1.5-flash")
response = model.generate_content("what are updates in java today")
print(response.text)