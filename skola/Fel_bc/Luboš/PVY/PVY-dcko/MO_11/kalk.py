import Tkinter as tki
import math

hl_okno=tki.Tk()

napis=tki.Label(hl_okno, text="")
napis.pack()
vstup=tki.Entry(hl_okno)
vstup.pack()

def pocitej():
    cislo = eval(vstup.get())
    napis.configure(text=cislo)
    
tlacitko=tki.Button(hl_okno, text="Pocitej", command=pocitej)
tlacitko.pack()

hl_okno=tki.mainloop()

