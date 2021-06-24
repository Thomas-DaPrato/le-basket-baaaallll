import pygame
from pygame.locals import*
import os

pygame.init()

def load_image(nomImg):
    return pygame.image.load(os.path.join("images",nomImg)).convert_alpha()