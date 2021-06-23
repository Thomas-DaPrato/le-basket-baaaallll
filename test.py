import pygame
from pygame.locals import*
import math

x = int(input())
t = 5
p = x % t

if p == 0:
    print(p)
    print('gagner')
else:
    print(p)
    print('perdu')
